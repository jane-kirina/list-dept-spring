let errors;

function validateDepartment(form) {
  errors = [];
  validateName(form);
  validatePhone(form);
  return errors.length !== 0 ? errors : null;
}

function validateEmployee(form) {
  errors = [];
  validateName(form);
  validateEmail(form);
  validateBirthDate(form);
  validateDepartmentId(form);
  validateYearsWorking(form);
  return errors.length !== 0 ? errors : null;
}

function validateName(form) {
  const email = form.name.value;

  if (email == null || email === '') {
    errors.push('Input name.');
  }
}

function validateEmail(form) {
  const email = form.email.value;

  const regx = '^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$';

  if (email == null || email === '') {
    errors.push('Input email.');
  } else if (!email.match(regx)) {
    errors.push('Invalid email.');
  }
}

function validatePhone(form) {
  const phone = form.number.value;

  const regx = '^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$';

  if (phone == null || phone === '') {
    errors.push('Input phone.');
  } else if (!phone.match(regx)) {
    errors.push('Invalid phone.');
  }
}

function validateBirthDate(form) {
  const birthDate = form.birthDate.value;

  if (birthDate == null || birthDate === '') {
    errors.push('Input phone.');
  } else if (Number.isNaN(Date.parse(birthDate))) {
    errors.push('Invalid birthDate.');
  } else if (getAge(birthDate) < 18) {
    errors.push('Employee must be over 18 years old.');
  }
}

function getAge(birthDateString) {
  const today = new Date();
  const birthDate = new Date(birthDateString);
  let age = today.getFullYear() - birthDate.getFullYear();
  const month = today.getMonth() - birthDate.getMonth();
  if (month < 0 || (month === 0 && today.getDate() < birthDate.getDate())) {
    age--;
  }
  return age;
}

function validateDepartmentId(form) {
  const departmentIdStr = form.departmentId.value;

  const departmentId = Number.parseInt(departmentIdStr, 10);

  if (Number.isNaN(departmentId)) {
    errors.push('Invalid id of department.');
  } else if (departmentId < 1) {
    errors.push('The id of department not valid.');
  }
}

function validateYearsWorking(form) {
  const yearsWorkingStr = form.yearsWorking.value;

  const yearsWorking = Number.parseInt(yearsWorkingStr, 10);

  if (Number.isNaN(yearsWorking)) {
    errors.push('Invalid years working.');
  } else if (yearsWorking > 100 || yearsWorking < 0) {
    errors.push('The number of years of work not valid.');
  }
}

export { validateEmployee, validateDepartment };
