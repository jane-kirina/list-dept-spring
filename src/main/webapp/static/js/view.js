import { createDivBackDept, createDivError, createDivWithErrors } from './component/template/divTemplate.js';
import { createText } from './component/template/componentTemplate.js';

const mainView = (title, header) => {
  document.title = title;
  document.getElementById('header').innerText = header;
  cleanContent();
};

const errorView = (errorMassage, title = 'Something went wrong', header = 'Error') => {
  mainView(title, header);

  const divContent = document.getElementById('content');
  const divError = createDivError();

  divError.appendChild(createText(errorMassage || '', 'h2'));

  divContent.appendChild(divError);
  divContent.appendChild(document.createElement('hr'));

  createDivBackDept();
};

const cleanContent = () => {
  const divContent = document.getElementById('content');
  while (divContent.firstChild) {
    divContent.removeChild(divContent.firstChild);
  }
};

const createErrorsDivForForm = (errors, form, parent) => {
  const div = document.getElementById('error');
  if (div) {
    div.remove();
  }

  const divErrors = createDivWithErrors(errors);
  parent.insertBefore(divErrors, form);
};

export {
  errorView,
  mainView,
  cleanContent,
  createErrorsDivForForm,
};
