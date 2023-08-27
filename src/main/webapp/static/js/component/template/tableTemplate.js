import {
  createButton, createLink, createList, createText,
} from './componentTemplate.js';
import { deleteDepartment } from '../../controller/departmentCommands.js';
import { deleteEmployee } from '../../controller/employeeCommands.js';

const employeesHeader = (deptId) => ({
  name: createText('Name', 'p'),
  birthDate: createText('Birthday', 'p'),
  yearsWorking: createText('Years Working', 'p'),
  email: createText('Email', 'p'),
  add: createLink(`#/department/${deptId}/employee/create`, 'Add employee', 'button'),
});

const departmentsHeader = {
  name: createText('Name', 'p'),
  number: createText('Phone Number', 'p'),
  add: createLink('#/department/create', 'Add department', 'button'),
};

const createListOfActionsDepartment = (id) => {
  const editButton = createButton('Edit');
  const listOfEmployeesButton = createButton('List of employees');
  const deleteButton = createButton('Delete');
  editButton.addEventListener('click', () => {
    window.location.href = `/#/department/${id}/update`;
  }, false);

  listOfEmployeesButton.addEventListener('click', () => {
    window.location.href = `/#/department/${id}/employee`;
  }, false);

  deleteButton.addEventListener('click', () => {
    deleteWarningDepartment(id);
  }, false);

  return createList({ editButton, listOfEmployeesButton, deleteButton });
};

const deleteWarningDepartment = (deptId) => {
  if (window.confirm('Are you sure you want to do this? When a department is deleted, employees from this department are also deleted')) {
    deleteDepartment(deptId);
  }
};

const createListOfActionsEmployee = (id, { deptId }) => {
  const editButton = createButton('Edit');
  const deleteButton = createButton('Delete');

  editButton.addEventListener('click', () => {
    window.location.href = `/#/department/${deptId}/employee/${id}/update`;
  }, false);

  deleteButton.addEventListener('click', () => {
    deleteWarningEmployee(deptId, id);
  }, false);

  return createList({ editButton, deleteButton });
};

const deleteWarningEmployee = (deptId, employeesId) => {
  if (window.confirm('Are you sure you want to do this?')) {
    deleteEmployee(deptId, employeesId);
  }
};
export {
  employeesHeader,
  departmentsHeader,
  createListOfActionsDepartment,
  createListOfActionsEmployee,
};
