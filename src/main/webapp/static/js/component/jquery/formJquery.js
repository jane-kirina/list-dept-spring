import { createDepartIdInput, createDepartIdLabel } from './template/componentTemplateJquery.js';

export const makeFormForEmployee = (employee, desiredText, deptId) => {
  const {
    id, name, birthDate, yearsWorking, email,
  } = employee;

  const $departmentIdInput = createDepartIdInput(deptId, id);
  const $departmentIdLabel = createDepartIdLabel(id);

  $('#content')
    .append($('<br/><hr/>'))
    .append($('<form />', { id: 'form' })
      .append(
        $('<br/>'),
        $('<label/>', { id: 'name', class: 'label' }).append('Name of employee'),
        $('<input/>', {
          id: 'name', value: name, placeholder: 'Name', type: 'text',
        }),
        $('<label/>', { id: 'email', class: 'label' }).append('Email'),
        $('<input/>', {
          id: 'email', value: email, placeholder: 'Email', type: 'text',
        }),

        $('<label/>', { id: 'yearsWorking', class: 'label' }).append('How many years has it been working'),
        $('<br/><br/>'),
        $('<input/>', {
          id: 'yearsWorking', value: yearsWorking, placeholder: 'Experience', type: 'number',
        }),
        $('<br/><br/>'),
        $('<label/>', { id: 'birthDate', class: 'label' }).append('Birthday'),
        $('<br/><br/>'),
        $('<input/>', {
          id: 'birthDate', value: birthDate, placeholder: 'Date Of Birth', type: 'date',
        }),
        $('<br/><br/>'),
        $departmentIdLabel,
        $('<br/><br/>'),
        $departmentIdInput,
        $('<hr/>'),
        $('<input/>', { id: 'id', value: id, type: 'hidden' }),
        $('<button/>', { id: 'button', class: 'add', type: 'submit' }).append(desiredText),
      ));
};
