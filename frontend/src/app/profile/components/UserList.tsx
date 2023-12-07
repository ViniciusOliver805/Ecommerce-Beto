'use client';
import { Button, Table, message } from 'antd';
import React, { useState, useEffect } from 'react';
import UserForm from './UserForm';
import axios from 'axios';
import moment from 'moment';

function UsersList() {
  const [showUserForm, setShowUserForm] = useState(false);
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(false);
  const [selectedUser, setSelectedUser] = useState<any>(null);
  const [loadingDelete, setLoadingDelete] = useState(false);

  const getUsers = async () => {
    try {
      setLoading(true);
      const res = await axios.get('http://localhost:8080/customer');
      setUsers(res.data);
    } catch (error: any) {
      message.error(error.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    getUsers();
  }, []);

  // delete user
  const onDelete = async (id: string) => {
    try {
      setLoadingDelete(true);
      await axios.delete(`http://localhost:8080/customer/${id}`);
      message.success('User deleted successfully');
      setSelectedUser(null);
      getUsers();
    } catch (error: any) {
      message.error(error.message);
    } finally {
      setLoadingDelete(false);
    }
  };

  const columns = [
    { title: 'ID', dataIndex: 'id', key: 'id' },
    {
      title: 'Name',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: 'CPF',
      dataIndex: 'cpf',
      key: 'cpf',
    },
    {
      title: 'Email',
      dataIndex: 'email',
      key: 'email',
    },
    {
      title: 'Birthday',
      dataIndex: 'birthday',
      key: 'birthday',
    },
    {
      title: 'Action',
      dataIndex: 'action',
      key: 'action',
      render: (action: any, params: any) => {
        return (
          <div className="flex gap-3 items-center">
            <Button
              type="primary"
              className="mr-2a btn-small"
              onClick={() => {
                setShowUserForm(true);
                setSelectedUser(params);
              }}
            >
              Edit
            </Button>
            <Button
              type="primary"
              danger
              className="btn-small"
              onClick={() => [setSelectedUser(params), onDelete(params.id)]}
              loading={loadingDelete && selectedUser?.id === params.id}
            >
              Delete
            </Button>
          </div>
        );
      },
    },
  ];

  return (
    <div>
      <div className="flex justify-end">
        <Button
          type="primary"
          onClick={() => {
            setShowUserForm(true);
          }}
        >
          Add User
        </Button>
      </div>

      <div className="mt-5">
        <Table
          dataSource={users}
          columns={columns}
          loading={loading}
          pagination={false}
        />
      </div>

      {showUserForm && (
        <UserForm
          showUserForm={showUserForm}
          setShowUserForm={setShowUserForm}
          reloadData={() => getUsers()}
          user={selectedUser}
          setSelectedUser={setSelectedUser}
        />
      )}
    </div>
  );
}

export default UsersList;
