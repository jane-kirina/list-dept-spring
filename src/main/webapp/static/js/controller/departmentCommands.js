import { convertErrors, convertFormToDepartment, convertObjectToDepartment } from '../util/convert.js';
import { createExchanger, exchange, handlerXMLHttpRequest } from '../exchange.js';
import {
  CREATE_DEPARTMENT, DELETE_DEPARTMENT, GET_DEPARTMENT, GET_DEPARTMENTS, UPDATE_DEPARTMENT,
} from './api.js';
import { createListOfActionsDepartment, departmentsHeader } from '../component/template/tableTemplate.js';
import { cleanContent, createErrorsDivForForm, errorView } from '../view.js';
import { validateDepartment } from '../util/validation.js';
import { createDivBackDept } from '../component/template/divTemplate.js';
import { makeFormForDepartment } from '../component/form.js';
import { dept } from '../model.js';
import { createFilledTable } from '../component/table.js';

const getListDepartments = () => {
  cleanContent();
  exchange(createExchanger(GET_DEPARTMENTS, 'GET', handlerXMLHttpRequest))
    .then((result) => {
      const listDepartments = [];

      result.forEach((object) => {
        const department = convertObjectToDepartment(object);
        listDepartments.push(department);
      });

      createFilledTable(listDepartments, departmentsHeader, createListOfActionsDepartment);
    })
    .catch(() => errorView('There is something'));
};

const getDepartment = (id) => {
  exchange(createExchanger(GET_DEPARTMENT(id), 'GET', handlerXMLHttpRequest))
    .then((result) => {
      const department = convertObjectToDepartment(result);

      makeFormForDepartment(department, 'Edit');
      createDivBackDept();

      addEventForButton(id);
    })
    .catch(() => errorView('Sorry, something went wrong :('));
};

const makeNewDepartment = () => {
  makeFormForDepartment(dept(), 'Add');
  createDivBackDept();

  addEventForButton();
};

const addEventForButton = (id) => {
  const button = document.getElementById('button');
  button.addEventListener('click', (event) => {
    event.preventDefault();
    sendDepartment(id);
  }, false);
};

const deleteDepartment = (id) => {
  exchange(createExchanger(DELETE_DEPARTMENT(id), 'DELETE', handlerXMLHttpRequest))
    .then(() => getListDepartments())
    .catch(() => errorView("Something went wrong. We can't delete data"));
};

const sendDepartment = (id) => {
  const form = document.getElementById('form');
  const errors = validateDepartment(form);
  const department = convertFormToDepartment(form);

  const div = document.getElementById('content');

  if (errors !== null) {
    createErrorsDivForForm(errors, form, div);
  } else {
    const exchanger = id === undefined
      ? createExchanger(CREATE_DEPARTMENT, 'POST', handlerXMLHttpRequest, department)
      : createExchanger(UPDATE_DEPARTMENT(id), 'PUT', handlerXMLHttpRequest, department);

    exchange(exchanger)
      .then(() => {
        window.location.href = '#/department';
      })

      .catch((errorFromServer) => {
        createErrorsDivForForm(convertErrors(errorFromServer), form, div);
      })
      .catch(() => errorView('Sorry, something went wrong'));
  }
};

export {
  getListDepartments,
  makeNewDepartment,
  getDepartment,

  deleteDepartment,
};
