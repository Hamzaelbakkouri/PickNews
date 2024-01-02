import { gql } from "apollo-angular";

export const GET_POST = gql`
    query  {
        postById(id : 5){
           id
           title
           content
           publishTime
           Tags
            publisher {
               id
               userName
               email
   }
  }
 }
`;