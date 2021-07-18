import * as React from "react";
import {
  Create,
  Edit,
  List,
  SimpleForm,
  Datagrid,
  TextField,
  NumberField,
  TextInput,
  NumberInput,
  DateField,
  EditButton,
} from "react-admin";

export const ItemList = (props) => (
  <List {...props}>
    <Datagrid>
      <NumberField source="itemId" label="品目ID" />
      <NumberField source="orderId" label="注文ID" />
      <NumberField source="productId" label="商品ID" />
      <NumberField source="price" label="価格" />
      <NumberField source="number" label="数量" />
      <DateField source="updateDate" label="更新日時" />
      <EditButton />
    </Datagrid>
  </List>
);

export const ItemCreate = (props) => (
  <Create {...props}>
    <SimpleForm>
      <NumberField source="itemId" label="品目ID" />
      <NumberInput source="orderId" label="注文ID" />
      <NumberInput source="productId" label="商品ID" />
      <NumberInput source="price" label="価格" />
      <NumberInput source="number" label="数量" />
    </SimpleForm>
  </Create>
);

export const ItemEdit = (props) => (
  <Edit {...props}>
    <SimpleForm>
      <NumberField source="itemId" label="品目ID" />
      <NumberInput source="orderId" label="注文ID" />
      <NumberInput source="productId" label="商品ID" />
      <NumberInput source="price" label="価格" />
      <NumberInput source="number" label="数量" />
    </SimpleForm>
  </Edit>
);
