# 🚀 Rapid.ly - A Modern URL Shortening Service

## 🌟 Overview

Rapid.ly is an advanced URL shortening service that enables users to create concise, shareable links with seamless redirection. The project employs modern web development techniques, featuring secure authentication, analytics tracking, and custom domain linking. It supports both path-based and subdomain-based routing for optimized usability and performance.

## 🔥 Features

- 🔑 **User Authentication**: Secure login and registration with JWT-based authentication.
- 🔗 **URL Shortening**: Generate unique short URLs for long links.
- 📊 **Analytics Dashboard**: Track URL usage statistics via graphical visualizations.
- 🔄 **Seamless Redirection**: Clean and structured routes for efficient URL resolution.
- 🌍 **Custom Domains**: Enable users to use their own domains for shortened URLs.
- 🏷 **Subdomain-Based Routing**: Support for routing URLs based on subdomains.
- 📍 **Path-Based Routing**: Direct users to different destinations via URL paths.
- 📋 **Clipboard Copying**: Easy one-click copy functionality for shortened URLs.
- 🔔 **Real-Time Notifications**: User feedback via toast notifications.
- ☁️ **Deployment on Cloud**: Hosted backend, frontend, and database using Render, Netlify, and Neon.

## 🛠 Tech Stack

### 🔙 Backend

- 🏗 **Spring Boot**: Framework for building scalable backend applications.
- 🔐 **Spring Security**: Handles authentication and authorization.
- 🗄 **Spring Data JPA**: Simplified database interactions with PostgreSQL.
- 🗃 **PostgreSQL**: Relational database for storing URLs and user data.
- ✨ **Lombok**: Reduces boilerplate code.
- 🏗 **Maven**: Dependency management and build automation.
- ☕ **Modern Java**: Leveraging Java for enterprise-grade backend development.

### 🎨 Frontend

- ⚛️ **React**: Component-based UI development.
- 🚏 **React Router Dom**: Client-side routing for seamless navigation.
- ⚡ **Vite**: Fast development and optimized production build system.

### 🖌 UI/UX

- 🏗 **MUI (Material UI)**: Modern UI components.
- 🎨 **Emotion**: Styled components for customizable styling.

### 📊 Data Management

- 🔄 **React Query**: Efficient data fetching and caching.
- 🌐 **Axios**: API request handling.
- 📝 **React Hook Form**: Simplified form validation and state management.

### 📈 Data Visualization

- 📉 **Chart.js & react-chartjs-2**: Interactive analytics and reports.

### 🛠 Utility Libraries

- 📅 **Day.js**: Lightweight date manipulation.
- 📋 **React Copy to Clipboard**: Quick copy functionality.
- 🔥 **React Hot Toast**: Non-intrusive notifications.

## ☁️ Deployment

- 🔙 **Backend**: Hosted on Render.
- 🌍 **Frontend**: Deployed via Netlify.
- 🗄 **Database**: Managed using Neon PostgreSQL.
- 🔗 **Domain Linking**: Custom domains linked to the deployed application.

## 📌 Setup Guide

### 🏗 Backend

1. Clone the repository and navigate to the backend directory.

2. Configure database settings in `application.properties`.

3. Run the application using Maven:

   ```sh
   mvn spring-boot:run
   ```

### 🎨 Frontend

1. Navigate to the frontend directory.

2. Install dependencies:

   ```sh
   npm install
   ```

3. Start the development server:

   ```sh
   npm run dev
   ```

## 🚀 Future Enhancements

- 📊 User dashboard with advanced analytics.
- 📸 QR code generation for shortened URLs.
- ⏳ Expiry-based URL functionality.
- 🛠 Admin panel for URL management.

Rapid.ly provides a seamless and modern approach to URL shortening with enhanced usability and security, making it a robust solution for personal and business needs.