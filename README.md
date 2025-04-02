# NGINX Reverse Proxy with SSL/TLS Termination

## Overview
This project sets up an NGINX server to act as a reverse proxy for a Spring Boot application running on port 8080. The reverse proxy handles incoming HTTP and HTTPS requests, providing SSL/TLS termination to ensure secure communication with clients.

## Features
- **Reverse Proxy**: NGINX forwards client requests to the backend Spring Boot application.
- **SSL/TLS Termination**: HTTPS requests are decrypted at NGINX before being forwarded to the backend over HTTP.
- **HTTP/2 Support**: Enables improved performance using HTTP/2.
- **Security Enhancements**: Uses strong TLS protocols and cipher suites.

## Configuration
### Prerequisites
- A running **Spring Boot** application on `http://springboot-app:8080`
- NGINX installed on your server
- SSL certificate and key files stored at `/etc/nginx/ssl/nginx.crt` and `/etc/nginx/ssl/nginx.key`

### NGINX Configuration File (`/etc/nginx/nginx.conf`)
```nginx
server {
    listen 80;
    server_name localhost;

    location / {
        proxy_pass http://springboot-app:8080;
        proxy_http_version 1.1;
        proxy_set_header Connection "";
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}

server {
    listen 443 ssl http2;
    server_name localhost;

    ssl_certificate /etc/nginx/ssl/nginx.crt;
    ssl_certificate_key /etc/nginx/ssl/nginx.key;

    ssl_protocols TLSv1.2 TLSv1.3;
    ssl_ciphers HIGH:!aNULL:!MD5;

    location / {
        proxy_pass http://springboot-app:8080;
        proxy_http_version 1.1;
        proxy_set_header Connection "";
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

## Installation & Setup
### 1. Install NGINX
```bash
sudo apt update && sudo apt install nginx -y
```

### 2. Add SSL Certificates
Place your SSL certificate and key files in `/etc/nginx/ssl/`:
```bash
sudo mkdir -p /etc/nginx/ssl
sudo cp your_certificate.crt /etc/nginx/ssl/nginx.crt
sudo cp your_private_key.key /etc/nginx/ssl/nginx.key
```

### 3. Configure NGINX
Modify the NGINX configuration file:
```bash
sudo nano /etc/nginx/sites-available/default
```
Replace its contents with the configuration provided above.

### 4. Restart NGINX
```bash
sudo systemctl restart nginx
```

## Testing
### 1. Verify NGINX is Running
```bash
systemctl status nginx
```

### 2. Test HTTP
Use Postman or `curl` to test HTTP:
```bash
curl -I http://localhost
```
Expected response:
```
HTTP/1.1 200 OK
```

### 3. Test HTTPS
Use Postman or `curl` to test HTTPS:
```bash
curl -I https://localhost --insecure
```
Expected response:
```
HTTP/2 200 OK
```

## Notes
- If you're using a domain name instead of `localhost`, replace `server_name localhost;` with your domain.
- Ensure your firewall allows traffic on ports **80** (HTTP) and **443** (HTTPS).
- For a production environment, obtain an SSL certificate from **Let's Encrypt** or another trusted Certificate Authority.

## License
This project is licensed under the MIT License.

## Author
**Falade Ayomide**

