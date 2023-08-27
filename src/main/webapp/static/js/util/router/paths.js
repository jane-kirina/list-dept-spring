import { mainView } from '../../view.js';
import {
  getDepartment,
  getListDepartments,
  makeNewDepartment,
} from '../../controller/departmentCommands.js';
import {
  getEmployee,
  getListEmployees,
  makeNewEmployee,
} from '../../controller/employeeCommands.js';

export const paths = [
  {
    path: '#/department',
    command: () => getListDepartments(),
    view: () => mainView('List of departments', 'Departments'),
  },
  {
    path: '#/department/create',
    command: () => makeNewDepartment(),
    view: () => mainView('New dept', 'Create department'),
  },
  {
    path: '#/department/:id/update',
    command: (deptId) => getDepartment(deptId),
    view: () => mainView('Edit Dept', 'Edit Department'),
  },

  {
    path: '#/department/:id/employee',
    command: (deptId) => getListEmployees(deptId),
    view: (deptId) => mainView('List of employees', `Employees in ${deptId} dept`),
  },
  {
    path: '#/department/:id/employee/create',
    command: (deptId) => makeNewEmployee(deptId),
    view: () => mainView('New employee', 'New employee'),
  },
  {
    path: '#/department/:id/employee/:id/update',
    command: (deptId, id) => getEmployee(deptId, id),
    view: () => mainView('Edit Employee', 'Edit employee'),
  },
];
