const dept = (id = '', name = '', number = '') => ({
  id,
  name,
  number,
});

const employee = (id = '', name = '', birthDate = '', yearsWorking = '', email = '', department = dept()) => ({
  id,
  name,
  birthDate,
  yearsWorking,
  email,
  department,
});

export {
  employee,
  dept,
};
