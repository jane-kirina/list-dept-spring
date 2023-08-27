import {
  CREATE_EMPLOYEE, DELETE_EMPLOYEE, GET_EMPLOYEE, GET_EMPLOYEES, UPDATE_EMPLOYEE,
} from './api.js';
import {
  createExchanger, exchange, handlerJquery,
} from '../exchange.js';
import {
  convertErrors,
  convertFormToEmployee,
  convertObjectToEmployee,
} from '../util/convert.js';
import {
  createListOfActionsEmployee,
  employeesHeader,
} from '../component/template/tableTemplate.js';
import { cleanContent, createErrorsDivForForm, errorView } from '../view.js';
import { validateEmployee } from '../util/validation.js';
import { createDivBackDept, createDivBackEmployee } from '../component/jquery/template/divTemplateJquery.js';
import { makeFormForEmployee } from '../component/jquery/formJquery.js';
import { employee } from '../model.js';
import { createFilledTable } from '../component/table.js';

const getListEmployees = (deptId) => {
  cleanContent();
  exchange(createExchanger(GET_EMPLOYEES(deptId), 'GET', handlerJquery))
    .then((result) => {
      const listEmployees = [];

      result.forEach((object) => {
        const empl = convertObjectToEmployee(object);
        listEmployees.push(empl);
      });

      createFilledTable(deleteDeptObject(listEmployees), employeesHeader(deptId),
        createListOfActionsEmployee, { deptId });

      createDivBackDept();
    })
    .catch(() => errorView('There is something'));
};

const deleteDeptObject = (listEmpl) => {
  const listEmployees = [];
  listEmpl.forEach((empl) => {
    const cloneElement = { ...empl };

    delete cloneElement.department;
    listEmployees.push(cloneElement);
  });
  return listEmployees;
};

const getEmployee = (deptId, id) => {
  exchange(createExchanger(GET_EMPLOYEE(deptId, id), 'GET', handlerJquery))
    .then((result) => {
      const empl = convertObjectToEmployee(result);

      makeFormForEmployee(empl, 'Edit', deptId);

      addEventForButton(deptId, id);

      createDivBackEmployee(`#/department/${deptId}/employee`, deptId);
    })
    .catch(() => errorView('Sorry, something went wrong :('));
};

const makeNewEmployee = (deptId) => {
  makeFormForEmployee(employee(), 'Add', deptId);
  addEventForButton(deptId);
  createDivBackEmployee(`#/department/${deptId}/employee`, deptId);
};

const addEventForButton = (deptId, id) => {
  $('button').click((event) => {
    event.preventDefault();
    sendEmployee(deptId, id);
  });
};

const deleteEmployee = (deptId, id) => {
  exchange(createExchanger(DELETE_EMPLOYEE(deptId, id), 'DELETE', handlerJquery))
    .then(() => getListEmployees(deptId))
    .catch(() => errorView("Something went wrong. We can't delete data"));
};

const sendEmployee = (deptId, id) => {
  const form = document.getElementById('form');
  const errors = validateEmployee(form);
  const empl = convertFormToEmployee(form);

  const div = document.getElementById('content');
  if (errors !== null) {
    createErrorsDivForForm(errors, form, div);
  } else {
    const exchanger = id === undefined
      ? createExchanger(CREATE_EMPLOYEE(deptId), 'POST', handlerJquery, empl)
      : createExchanger(UPDATE_EMPLOYEE(deptId, id), 'PUT', handlerJquery, empl);

    exchange(exchanger)
      .then(() => {
        window.location.href = `#/department/${deptId}/employee`;
      })

      .catch((errorFromServer) => {
        createErrorsDivForForm(convertErrors(errorFromServer), form, div);
      })
      .catch(() => errorView('Sorry, something went wrong'));
  }
};

export {
  getListEmployees,
  getEmployee,
  makeNewEmployee,

  deleteEmployee,
};
