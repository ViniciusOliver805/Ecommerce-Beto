import React from 'react';
import { Form, Modal, message } from 'antd';
import { getAntdFieldsRequireRule } from '@/helpers/validations';
import axios from 'axios';

type UserFormValues = {
  id: string;
  name: string;
  cpf: string;
  email: string;
  birthday: string;
};

function UserForm({
  showUserForm,
  setShowUserForm,
  reloadData,
  user,
  setSelectedUser,
}: UserFormProps) {
  const [form] = Form.useForm();

  const onFinish = async (values: UserFormValues) => {
    try {
      if (user) {
        await axios.patch(
          `http://localhost:8080/customer/${user.id}`,
          values
        );
        message.success('User updated successfully');
      } else {
        const res = await axios.post('http://localhost:8080/customer', values);
        console.log(res);
        message.success('User added successfully');
      }
      reloadData();
      setShowUserForm(false);
      setSelectedUser(null);
    } catch (error: any) {
      message.error(error.message);
    }
  };

  return (
    <Modal
      title={
        <h1 className="text-2xl font-bold text-gray-800">
          {user ? 'Edit User' : 'Add User'}
        </h1>
      }
      visible={showUserForm}
      onCancel={() => {
        setShowUserForm(false);
        setSelectedUser(null);
      }}
      centered
      closable={false}
      okText="Save"
      onOk={() => {
        form.submit();
      }}
    >
      <Form
        layout="vertical"
        className="flex flex-col gap5"
        form={form}
        onFinish={onFinish}
        initialValues={user}
      >
        <Form.Item
          label="Name"
          name="name"
          rules={getAntdFieldsRequireRule('Name is required')}
        >
          <input type="text" />
        </Form.Item>
        <Form.Item
          label="CPF"
          name="cpf"
          rules={getAntdFieldsRequireRule('CPF is required')}
        >
          <input type="text" />
        </Form.Item>
        <Form.Item
          label="Email"
          name="email"
          rules={[
            ...getAntdFieldsRequireRule('Email is required'),
            { type: 'email', message: 'Invalid email address' },
          ]}
        >
          <input type="text" />
        </Form.Item>
        <Form.Item
          label="Birthday"
          name="birthday"
          rules={getAntdFieldsRequireRule('Birthday is required')}
        >
          <input type="text" />
        </Form.Item>
      </Form>
    </Modal>
  );
}

export default UserForm;

interface UserFormProps {
  showUserForm: boolean;
  setShowUserForm: (show: boolean) => void;
  reloadData: () => void;
  user: any;
  setSelectedUser: (user: any) => void;
}
