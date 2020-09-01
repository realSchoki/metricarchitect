FROM node

EXPOSE 3001

COPY ./ /app 

WORKDIR /app
RUN npm install && \
    ls
CMD [ "node", "index.js" ]