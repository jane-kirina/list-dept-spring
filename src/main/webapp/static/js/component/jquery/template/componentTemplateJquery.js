function createDepartIdInput(deptId, id) {
  return id
    ? $('<input/>', {
      id: 'departmentId', value: deptId, placeholder: 'Id department', type: 'number',
    })
    : $('<input/>', { id: 'departmentId', value: deptId, type: 'hidden' });
}

function createDepartIdLabel(id) {
  let $departmentIdLabel;

  if (id) {
    $departmentIdLabel = $('<label/>', { id: 'departmentId', class: 'label' });
    $departmentIdLabel.append('In which department does employee work');
  } else {
    $departmentIdLabel = $('<span/>');
  }

  return $departmentIdLabel;
}

export {
  createDepartIdLabel,
  createDepartIdInput,
};
