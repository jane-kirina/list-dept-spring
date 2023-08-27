import { createText } from './template/componentTemplate.js';

export const createFilledTable = (listOfElements, header, listOfActions, paramForActions) => {
  const table = createTableWithHeader(header);

  listOfElements.forEach((element) => {
    const tr = createTrWithItem(element, listOfActions, paramForActions);

    table.appendChild(tr);
  });

  document.getElementById('content').appendChild(table);
};

const createTableWithHeader = (header) => {
  const table = document.createElement('table');
  table.className = 'table';

  Object.keys(header).forEach((key) => {
    const th = document.createElement('th');
    th.appendChild(header[key]);
    table.appendChild(th);
  });

  return table;
};

const createTrWithItem = (element, listOfActions, paramForActions) => {
  const tr = document.createElement('tr');

  const { id } = element;
  const cloneElement = { ...element };

  delete cloneElement.id;

  Object.keys(cloneElement).forEach((key) => {
    const td = document.createElement('td');
    const item = createText(cloneElement[key], 'p');
    td.appendChild(item);
    tr.appendChild(td);
  });

  const tdWithList = document.createElement('td');
  tdWithList.appendChild(listOfActions(id, paramForActions));
  tr.appendChild(tdWithList);

  return tr;
};
