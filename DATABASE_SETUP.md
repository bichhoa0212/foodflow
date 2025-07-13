# Database Setup Guide

## Overview
This application uses MySQL database with JPA/Hibernate for automatic table creation and data initialization.

## Database Configuration

### 1. MySQL Server Setup
- MySQL version: 8.0 or higher
- Create a database user with appropriate permissions
- Database will be created automatically if it doesn't exist

### 2. Application Properties
The database configuration is in `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://192.168.21.174:3307/foodflow?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=foodflow
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### 3. Table Structure
The application creates the following tables:

- **app_users**: User accounts
- **app_roles**: System roles (ADMIN, USER, MODERATOR)
- **app_permissions**: System permissions
- **user_groups**: User groups
- **user_roles**: User-role relationships
- **role_permissions**: Role-permission relationships
- **group_users**: Group-user relationships

### 4. Default Data
The application automatically inserts:

#### Default Permissions:
- user:read, user:write, user:delete
- role:read, role:write, role:delete
- permission:read, permission:write, permission:delete
- group:read, group:write, group:delete
- admin:all

#### Default Roles:
- ADMIN: Full system access
- USER: Basic access
- MODERATOR: Limited administrative access

#### Default Users:
- **Admin**: admin@foodflow.com / 123456
- **User**: user@foodflow.com / 123456
- **Moderator**: moderator@foodflow.com / 123456

### 5. Running the Application

1. **First Run**: 
   - Set `spring.jpa.hibernate.ddl-auto=create-drop` in application.properties
   - Run the application
   - Tables will be created and default data inserted

2. **Subsequent Runs**:
   - Set `spring.jpa.hibernate.ddl-auto=update` in application.properties
   - This preserves existing data and only updates schema if needed

### 6. Troubleshooting

#### SQL Syntax Errors:
- Ensure MySQL version is 8.0 or higher
- Check that database user has CREATE, DROP, INSERT permissions
- Verify database connection settings

#### Table Creation Issues:
- Check MySQL server is running
- Verify database credentials
- Ensure database exists or can be created

#### Data Insertion Issues:
- Check foreign key constraints
- Verify data.sql file is in classpath
- Check for duplicate key violations

### 7. Manual Database Setup (Optional)

If you prefer to create tables manually:

1. Create database:
```sql
CREATE DATABASE foodflow CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. Create user:
```sql
CREATE USER 'foodflow'@'%' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON foodflow.* TO 'foodflow'@'%';
FLUSH PRIVILEGES;
```

3. Run schema.sql and data.sql manually

### 8. Security Notes

- Change default passwords in production
- Use strong database passwords
- Restrict database user permissions as needed
- Enable SSL for database connections in production
- Use environment variables for sensitive configuration

## API Endpoints

After setup, you can test the application:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Health Check**: http://localhost:8080/actuator/health
- **API Docs**: http://localhost:8080/api-docs 