import ky from "ky";

const apiPath = "http://localhost:8080/api";

export const api = ky.extend({
  prefixUrl: `${apiPath}`,
  timeout: 60000,
});
