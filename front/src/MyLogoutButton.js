import React from "react";
import { connect } from "react-redux";
import { Responsive, userLogout } from "react-admin";
import MenuItem from "@material-ui/core/MenuItem";
import Button from "@material-ui/core/Button";
import ExitIcon from "@material-ui/icons/PowerSettingsNew";

const MyLogoutButton = ({ userLogout, ...rest }) => (
  <Responsive
    xsmall={
      <MenuItem onClick={userLogout} {...sanitizeRestProps(rest)}>
        <ExitIcon /> Logout
      </MenuItem>
    }
    medium={
      <Button onClick={userLogout} size="small" {...sanitizeRestProps(rest)}>
        <ExitIcon /> Logout
      </Button>
    }
  />
);
export default connect(undefined, { userLogout })(MyLogoutButton);
