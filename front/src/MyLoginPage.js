import * as React from "react";
import { useState } from "react";
import { useLogin, useNotify, Notification, defaultTheme } from "react-admin";
import { ThemeProvider } from "@material-ui/styles";
import { createTheme } from "@material-ui/core/styles";

let requested = false;
const MyLoginPage = ({ theme }) => {
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const login = useLogin();
  const notify = useNotify();

  const submit = (e) => {
    if (name.trim() == "" || password.trim() == "") {
      notify("ユーザー名、パスワードの入力が必要です");
    } else if (requested) {
      notify("既にリクエスト中です");
    } else {
      e.preventDefault();
      requested = true;
      login({ name, password })
        .then((res) => {
          console.info(res);
        })
        .catch((e) => {
          notify(e.message);
        })
        .finally(() => {
          requested = false;
        });
    }
  };

  return (
    <ThemeProvider theme={theme}>
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
