const createExchanger = (url, method, handler, data) => ({
  url,
  method,
  data,
  handler,
});

const exchange = (exchanger) => callRequestMethod(exchanger)
  .then((result) => result);

const callRequestMethod = (exchanger) => new Promise((resolve, reject) => {
  try {
    exchanger.handler(resolve, reject, exchanger);
  } catch (e) {
    reject(e);
  }
});

const handlerJquery = (resolve, reject, exchanger) => {
  const { url, method, data } = exchanger;

  $.ajax({
    url,
    cache: false,
    type: method,
    contentType: 'application/json',
    data: JSON.stringify(data) || data,
    statusCode: {
      200(response) {
        resolve(response);
      },
      418(response) {
        reject(JSON.parse(response.responseText));
      },
    },
  });
};

const handlerXMLHttpRequest = (resolve, reject, exchanger) => {
  const request = new XMLHttpRequest();
  request.responseType = 'json';
  const { url, method, data } = exchanger;
  request.open(method, url, true);

  request.onload = () => {
    if (request.readyState === 4 && request.status < 300) {
      resolve(request.response);
    } else if (request.readyState === 4 && request.status === 418) {
      reject(request.response);
    } else {
      reject(new Error());
    }
  };

  request.setRequestHeader('Content-Type', 'application/json');
  request.send(JSON.stringify(data) || data);
};

export {
  createExchanger,
  exchange,
  handlerXMLHttpRequest,
  handlerJquery,
};
