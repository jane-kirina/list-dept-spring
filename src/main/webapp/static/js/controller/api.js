export const GET_DEPARTMENTS = '/department';
export const CREATE_DEPARTMENT = '/department/create-data';
export const UPDATE_DEPARTMENT = (id) => `/department/update-data/${id}/`;
export const GET_DEPARTMENT = (id) => `/department/update/${id}/`;
export const DELETE_DEPARTMENT = (id) => `/department/delete/${id}/`;

export const GET_EMPLOYEES = (deptId) => `/department/${deptId}/employee`;
export const CREATE_EMPLOYEE = (deptId) => `/department/${deptId}/employee/create-data`;
export const UPDATE_EMPLOYEE = (deptId, id) => `/department/${deptId}/employee/update-data/${id}`;
export const GET_EMPLOYEE = (deptId, id) => `/department/${deptId}/employee/update/${id}`;
export const DELETE_EMPLOYEE = (deptId, id) => `/department/${deptId}/employee/delete/${id}`;
