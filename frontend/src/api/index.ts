import ky from "ky";

const apiPath = "http://localhost:8080/api";

export const api = ky.extend({
  prefixUrl: `${apiPath}`, // base URL
  timeout: 60000, // optional timeout
});
