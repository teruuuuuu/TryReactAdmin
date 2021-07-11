import React from "react";
import ReactDOM from "react-dom";
import app from "./App";
console.log("hello webpack");

const rootElement = document.getElementById("app");
ReactDOM.render(<div>{app()}</div>, rootElement);
