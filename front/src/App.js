import * as React from "react";
import { Admin, Resource, ListGuesser } from "react-admin";
import jsonServerProvider from "ra-data-json-server";
import authProvider from "./authProvider";
import MyLoginPage from "./MyLoginPage";
import MyLogoutButton from "./MyLogoutButton";
import { PostList } from "./posts";

const dataProvider = jsonServerProvider("https://jsonplaceholder.typicode.com");
const App = () => (
  <Admin
    loginPage={MyLoginPage}
    logoutButton={MyLogoutButton}
    dataProvider={dataProvider}
    authProvider={authProvider}
  >
    <Resource name="posts" list={PostList} />
    <Resource name="users" list={ListGuesser} />
  </Admin>
);

export default App;

// in src/App.js
// import * as React from "react";
// import { Admin, Resource, ListGuesser } from "react-admin";
// import jsonServerProvider from "ra-data-json-server";
// import authProvider from "./authProvider";

// import MyLoginPage from "./MyLoginPage";
// import MyLogoutButton from "./MyLogoutButton";

// const dataProvider = jsonServerProvider("https://jsonplaceholder.typicode.com");
// const App = () => (
//   <Admin
//     loginPage={MyLoginPage}
//     logoutButton={MyLogoutButton}
//     dataProvider={dataProvider}
//     authProvider={authProvider}
//   >
//     <Resource name="users" list={ListGuesser} />
//     <Resource name="users" list={ListGuesser} />
//   </Admin>
// );

// export default App;
