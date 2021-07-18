import { fetchUtils } from "react-admin";
import { myJsonProvider } from "./jsonProvider";
import simpleRestProvider from "ra-data-simple-rest";

const httpClient = (url, options = {}) => {
  if (!options.headers) {
    options.headers = new Headers({
      Accept: "application/json",
    });
  }
  options.credentials = "include";
  return fetchUtils.fetchJson(url, options);
};

export default myJsonProvider(
  "http://localhost:8080",
  { items: "itemId" },
  httpClient
);
