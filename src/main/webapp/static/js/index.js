import Router from './util/router/router.js';

const router = new Router();

window.addEventListener('hashchange', () => {
  const { hash } = window.location;
  router.dispatch(hash);
});

const createTemplatePage = () => {
  if (!document.getElementById('container')) {
    const divBody = document.createElement('div');
    const divHeader = document.createElement('div');
    const divContent = document.createElement('div');

    divBody.id = 'container';
    divHeader.id = 'header';
    divContent.id = 'content';

    divBody.appendChild(divHeader);
    divBody.appendChild(document.createElement('br'));
    divBody.appendChild(divContent);

    document.body.append(divBody);
  }
};

createTemplatePage();

window.location.href = '/#/department';
