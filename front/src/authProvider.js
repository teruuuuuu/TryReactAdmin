import { AUTH_CHECK, AUTH_LOGIN, AUTH_LOGOUT, AUTH_ERROR } from "react-admin";

export default (type, params) => {
  if (type === AUTH_CHECK) {
    return localStorage.getItem("name") ? Promise.resolve() : Promise.reject();
  } else if (type === AUTH_LOGIN) {
    const { company, group, name, password } = params;
    const request = new Request("http://localhost:8080/login", {
      method: "POST",
      body: JSON.stringify({ company, group, name, password }),
      headers: new Headers({ "Content-Type": "application/json" }),
    });
    return fetch(request)
      .then((response) => {
        if (response.status < 200 || response.status >= 300) {
          throw new Error(response.statusText);
        }
        return response.json();
      })
      .then((result) => {
        if (result.hasOwnProperty("name")) {
          localStorage.setItem("name", result.name);
        }
      });
  } else if (type === AUTH_LOGOUT) {
  } else if (type === AUTH_ERROR) {
  }
  return Promise.resolve();
};
