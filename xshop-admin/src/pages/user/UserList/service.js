import request from "@/utils/request";

export async function queryUsers(params) {
  return request('/user/userList', {
    method: "POST",
    data: params
  },);
}

export async function getAuthGroups() {
  return request('/auth/getAuthGroupDropDown', {
    method: "GET",
  },);
}

export async function getUserByUid(params) {
  return request('/user/getUser', {
    method: "POST",
    data: params
  },);
}