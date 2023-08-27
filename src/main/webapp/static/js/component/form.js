import {
  createButton,
  createForm,
  createInput,
  createLabel,
} from './template/componentTemplate.js';

const inputAttributes = (type, placeholder, value, id) => ({
  type,
  placeholder,
  value,
  id,
});

export const makeFormForDepartment = (department, buttonText) => {
  const div = document.getElementById('content');
  div.appendChild(document.createElement('br'));
  const form = createForm();

  const labelName = createLabel('name', 'Name of new department');
  const inputName = createInput(inputAttributes('text', 'Name', department.name, 'name'));

  const labelPhone = createLabel('number', 'Department phone number');
  const inputPhone = createInput(inputAttributes('text', 'Phone', department.number, 'number'));

  const inputId = createInput(inputAttributes('hidden', '', department.id, 'id'));

  const button = createButton(buttonText, 'add');

  form.appendChild(document.createElement('hr'));
  form.appendChild(labelName);
  form.appendChild(inputName);
  form.appendChild(labelPhone);
  form.appendChild(inputPhone);
  form.appendChild(inputId);
  form.appendChild(document.createElement('hr'));
  form.appendChild(button);

  div.appendChild(form);
};
