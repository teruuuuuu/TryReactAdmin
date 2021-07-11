import * as React from "react";
import { useState } from "react";
import { useLogin, useNotify, Notification, defaultTheme } from "react-admin";
import { ThemeProvider } from "@material-ui/styles";
import { createMuiTheme } from "@material-ui/core/styles";

const MyLoginPage = ({ theme }) => {
  const [company, setCompany] = useState("");
  const [group, setGroup] = useState("");
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const login = useLogin();
  const notify = useNotify();
  const submit = (e) => {
    e.preventDefault();
    // will call authProvider.login({ email, password })
    login({ company, group, name, password }).catch(() =>
      notify("Invalid email or password")
    );
  };

  return (
    <ThemeProvider theme={createMuiTheme(defaultTheme)}>
      <input
        name="name"
        type="text"
        value={company}
        onChange={(e) => setCompany(e.target.value)}
      />
      <br />
      <input
        name="name"
        type="text"
        value={group}
        onChange={(e) => setGroup(e.target.value)}
      />
      <br />
      <form onSubmit={submit}>
        <input
          name="name"
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <br />
        <input
          name="password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <br />
        <input type="submit" value="ログイン" />
      </form>
      <Notification />
    </ThemeProvider>
  );
};

export default MyLoginPage;
