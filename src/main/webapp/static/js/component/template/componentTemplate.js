const createForm = () => {
  const form = document.createElement('form');

  form.id = 'form';

  return form;
};

const createLink = (href, desiredText, style) => {
  const a = document.createElement('a');

  a.href = href;
  a.className = style;
  a.appendChild(document.createTextNode(desiredText));

  return a;
};

const createButton = (desiredText, style = 'button', id = 'button') => {
  const button = document.createElement('button');

  button.setAttribute('type', 'submit');
  button.className = style;
  button.id = id;
  button.appendChild(document.createTextNode(desiredText));

  return button;
};

const createInput = ({
  type, placeholder, value, id,
}) => {
  const inputName = document.createElement('input');

  inputName.type = type;
  inputName.placeholder = placeholder;
  inputName.value = value;
  inputName.id = id;

  return inputName;
};

const createLabel = (id, text) => {
  const label = document.createElement('label');

  label.setAttribute('for', id);
  label.setAttribute('class', 'label');
  label.appendChild(document.createTextNode(text));

  return label;
};

const createText = (txt, level) => {
  const text = document.createElement(level);

  text.appendChild(document.createTextNode(txt));

  return text;
};

const createList = (objectWithElement) => {
  const ul = document.createElement('ul');

  Object.keys(objectWithElement).forEach((key) => {
    const li = document.createElement('li');
    li.appendChild(objectWithElement[key]);
    ul.appendChild(li);
  });

  return ul;
};

export {
  createForm,
  createLink,
  createButton,
  createInput,
  createLabel,
  createText,
  createList,
};
