import {
  dept, employee,
} from '../model.js';

const convertObjectToEmployee = ({
  id, name, birthDate, yearsWorking, email, department,
}) => {
  const bd = new Date(birthDate[0], birthDate[2], birthDate[1]).toISOString().split('T')[0];

  return employee(id, name, bd, yearsWorking, email, convertObjectToDepartment(department));
};

const convertObjectToDepartment = ({ id, name, number }) => dept(id, name, number);

const convertFormToEmployee = (form) => {
  const id = form.id !== undefined ? form.id.value : '';
  const name = form.name.value;

  const birthDate = form.birthDate.value;

  const yearsWorking = form.yearsWorking.value;
  const email = form.email.value;
  const departmentId = form.departmentId.value;
  const department = dept(departmentId);

  return employee(id, name, birthDate, yearsWorking, email, department);
};

const convertFormToDepartment = (form) => {
  const id = form.id.value;
  const name = form.name.value;
  const number = form.number.value;

  return dept(id, name, number);
};

const convertErrors = (result) => {
  const errors = [];
  result.forEach((error) => {
    errors.push(error.defaultMessage);
  });
  return errors;
};

export {
  convertFormToEmployee,
  convertFormToDepartment,
  convertObjectToDepartment,
  convertObjectToEmployee,
  convertErrors,
};
