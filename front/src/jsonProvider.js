import { fetchUtils } from "react-admin";
import { stringify } from "query-string";

export const myJsonProvider = (apiUrl, idMap, httpClient) => ({
  getList: (resource_type, params) => {
    const { page, perPage } = params.pagination;
    const { field, order } = params.sort;
    const query = {
      ...fetchUtils.flattenObject(params.filter),
      _sort: field,
      _order: order,
      _start: (page - 1) * perPage,
      _end: page * perPage,
    };
    const url = `${apiUrl}/${resource_type}?${stringify(query)}`;

    return httpClient(url).then(({ headers, json }) => {
      if (!headers.has("x-total-count")) {
        throw new Error(
          "The X-Total-Count header is missing in the HTTP Response. The jsonServer Data Provider expects responses for lists of resources to contain this header with the total number of results to build the pagination. If you are using CORS, did you declare X-Total-Count in the Access-Control-Expose-Headers header?"
        );
      }
      return {
        data: json.map((resource) => ({
          ...resource,
          id: resource[idMap[resource_type]],
        })),
        total: parseInt(headers.get("x-total-count").split("/").pop(), 10),
      };
    });
  },

  getOne: (resource_type, params) =>
    httpClient(`${apiUrl}/${resource_type}/${params.id}`).then((ret) => {
      console.info(ret);
      return {
        data: { ...ret.json, id: ret.json[idMap[resource_type]] },
      };
    }),

  getMany: (resource_type, params) => {
    const query = {
      id: params.ids,
    };
    const url = `${apiUrl}/${resource_type}?${stringify(query)}`;
    return httpClient(url).then(({ json }) => ({
      data: json.map((resource) => ({
        ...resource,
        id: resource[idMap[resource_type]],
      })),
    }));
  },

  getManyReference: (resource_type, params) => {
    const { page, perPage } = params.pagination;
    const { field, order } = params.sort;
    const query = {
      ...fetchUtils.flattenObject(params.filter),
      [params.target]: params.id,
      _sort: field,
      _order: order,
      _start: (page - 1) * perPage,
      _end: page * perPage,
    };
    const url = `${apiUrl}/${resource_type}?${stringify(query)}`;

    return httpClient(url).then(({ headers, json }) => {
      if (!headers.has("x-total-count")) {
        throw new Error(
          "The X-Total-Count header is missing in the HTTP Response. The jsonServer Data Provider expects responses for lists of resources to contain this header with the total number of results to build the pagination. If you are using CORS, did you declare X-Total-Count in the Access-Control-Expose-Headers header?"
        );
      }
      return {
        data: json.map((resource) => ({
          ...resource,
          id: resource[idMap[resource_type]],
        })),
        total: parseInt(headers.get("x-total-count").split("/").pop(), 10),
      };
    });
  },

  update: (resource_type, params) =>
    httpClient(`${apiUrl}/${resource_type}/${params.id}`, {
      method: "PUT",
      body: JSON.stringify(params.data),
    }).then(({ json }) => ({
      data: { ...json, id: json[idMap[resource_type]] },
    })),

  // json-server doesn't handle filters on UPDATE route, so we fallback to calling UPDATE n times instead
  updateMany: (resource_type, params) =>
    Promise.all(
      params.ids.map((id) =>
        httpClient(`${apiUrl}/${resource_type}/${id}`, {
          method: "PUT",
          body: JSON.stringify(params.data),
        })
      )
    ).then(({ json }) => ({
      data: json.map((resource) => ({
        ...resource,
        id: resource[idMap[resource_type]],
      })),
    })),

  create: (resource_type, params) =>
    httpClient(`${apiUrl}/${resource_type}`, {
      method: "POST",
      body: JSON.stringify(params.data),
    }).then(({ json }) => ({
      data: { ...json, id: json[idMap[resource_type]] },
    })),

  delete: (resource_type, params) =>
    httpClient(`${apiUrl}/${resource_type}/${params.id}`, {
      method: "DELETE",
    }).then(({ json }) => ({ data: json })),

  // json-server doesn't handle filters on DELETE route, so we fallback to calling DELETE n times instead
  deleteMany: (resource_type, params) =>
    Promise.all(
      params.ids.map((id) =>
        httpClient(`${apiUrl}/${resource_type}/${id}`, {
          method: "DELETE",
        })
      )
    ).then((responses) => ({
      data: responses.map(({ json }) => json[idMap[resource_type]]),
    })),
});
