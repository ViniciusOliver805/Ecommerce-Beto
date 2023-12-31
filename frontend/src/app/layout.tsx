import ThemeProvider from '@/provider/ThemeProvider';
import './globals.css';
import type { Metadata } from 'next';
import LayoutProvider from '@/provider/LayoutProvider';

export const metadata: Metadata = {
  title: 'Framework Shop',
  description: 'Generated by create next app',
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <head>
        <link
          href="https://cdn.jsdelivr.net/npm/remixicon@3.5.0/fonts/remixicon.css"
          rel="stylesheet"
        ></link>
      </head>
      <body>
        <ThemeProvider>
          <LayoutProvider>{children}</LayoutProvider>
        </ThemeProvider>
      </body>
    </html>
  );
}
