import { User } from "./UserType"

export interface Post {
    id : number
    title : string
    content : string
    publishTime :Date
    Tags : [string]
    publisher : User
}