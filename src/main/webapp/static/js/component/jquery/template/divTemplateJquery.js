const createDivBackEmployee = (urlEmpl, deptId) => {
  $('#content').append(
    $('<div/>', { class: 'container back' }).append(
      $('<p/>', { text: 'Back to ' }).append(
        $('<a/>', { href: '#/department', text: 'list of departments' }),
      ),
      $('<p/>', { text: ' or ' }).append(
        $('<a/>', { href: urlEmpl, text: `list of employee in ${deptId} dept` }),
      ),
    ),
  );
};

const createDivBackDept = () => {
  $('#content').append(
    $('<div/>', { class: 'container back' }).append(
      $('<p/>', { text: 'Back to ' }).append(
        $('<a/>', { href: '#/department', text: 'list of departments' }),
      ),
    ),
  );
};

export {
  createDivBackEmployee,
  createDivBackDept,
};
