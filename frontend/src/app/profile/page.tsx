'use client';
import React, { useState } from 'react';
import { Tabs, TabsProps } from 'antd';
import CategoriesList from './components/CategoriesList';
import UserList from './components/UserList';
import { useRouter, useSearchParams } from 'next/navigation';

function Profile() {
  const searchParams = useSearchParams();
  const id = searchParams.get('id');
  const [selectedTab, setSelectedTab] = useState<string>(id || '1');
  const router = useRouter();
  const items: TabsProps['items'] = [
    {
      key: '1',
      label: 'User',
      children: <UserList />,
    },
    {
      key: '2',
      label: 'Products',
      children: 'Products Page',
    },
    {
      key: '3',
      label: 'Categories',
      children: <CategoriesList />,
    },
    {
      key: '4',
      label: 'Orders',
      children: 'Orders Page',
    },
  ];

  return (
    <div className="p-5">
      <Tabs
        defaultActiveKey="1"
        items={items}
        onChange={(key) => {
          router.push(`/profile?id=${key}`);
          setSelectedTab(key);
        }}
        activeKey={selectedTab}
      ></Tabs>
    </div>
  );
}

export default Profile;
