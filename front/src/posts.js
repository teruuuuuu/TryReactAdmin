// in src/posts.js
import * as React from "react";
import { connect } from "react-redux";
import {
  List,
  Datagrid,
  TextField,
  DateField,
  BooleanField,
} from "react-admin";

export const PostList = (props) => (
  <List {...props}>
    <Datagrid>
      <TextField source="id" />
      <TextField source="title" />
      <DateField source="published_at" />
      <TextField source="category" />
      <BooleanField source="commentable" />
    </Datagrid>
  </List>
);

// export default connect(undefined, {})(PostList);
