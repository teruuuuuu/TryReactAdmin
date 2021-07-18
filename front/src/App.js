import * as React from "react";
import { fetchUtils, Admin, Resource, ListGuesser } from "react-admin";
import jsonServerProvider from "ra-data-json-server";
import authProvider from "./authProvider";
import MyLoginPage from "./MyLoginPage";
import MyLogoutButton from "./MyLogoutButton";
import { PostList } from "./posts";
import { ItemList, ItemCreate, ItemEdit } from "./items";
import dataproviders from "./dataproviders";
import { createTheme } from "@material-ui/core/styles";

const App = () => (
  <Admin
    loginPage={MyLoginPage}
    logoutButton={MyLogoutButton}
    dataProvider={dataproviders}
    authProvider={authProvider}
    theme={createTheme({})}
  >
    <Resource
      name="items"
      options={{ label: "品目" }}
      list={ItemList}
      create={ItemCreate}
      edit={ItemEdit}
    />
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
