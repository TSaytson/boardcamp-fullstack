FROM node:20

WORKDIR /tmp/react

COPY package*.json ./
RUN npm i

COPY . .
RUN npm run build

RUN mkdir -p /var/www/html

RUN mv dist/* /var/www/html

WORKDIR /

RUN rm -rf /tmp/react