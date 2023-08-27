import { createLink, createText } from './componentTemplate.js';

const createDivError = () => {
  const divError = document.createElement('div');

  divError.className = 'container error';

  return divError;
};

const createDivWithErrors = (errors) => {
  const divError = document.createElement('div');

  divError.className = 'container error';
  divError.id = 'error';

  errors.forEach((error) => {
    const err = createText(error, 'p');
    divError.appendChild(err);
  });

  return divError;
};

const createDivBack = () => {
  const divBack = document.createElement('div');

  divBack.className = 'container back';

  return divBack;
};

const createDivBackDept = () => {
  const divBack = createDivBack();

  const backToListDeptLink = createLink('#/department', 'Back to list of departments');

  divBack.appendChild(backToListDeptLink);

  document.getElementById('content').appendChild(divBack);
};

export {
  createDivBack,
  createDivError,
  createDivWithErrors,
  createDivBackDept,
};
