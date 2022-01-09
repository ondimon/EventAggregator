# syntax=docker/dockerfile:1
#stage 1
FROM node:12.22.5-alpine as node
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .

FROM node as dev
CMD ["npm", "start", "--", "--configuration", "docker", "--host", "0.0.0.0", "--disable-host-check"]

FROM node as build
WORKDIR /app
RUN npm run build --prod

#stage 2
FROM nginx:alpine
COPY --from=build /app/dist/web-gui /usr/share/nginx/html
