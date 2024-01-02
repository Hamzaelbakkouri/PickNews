import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Observable, map, of } from 'rxjs';
import { Apollo } from 'apollo-angular';
import { Post } from 'src/app/Types/PostTypes';
import { GET_POST } from 'src/app/Services/Querys/QueryPost';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  constructor(private apollo: Apollo) { }
  
  PostById: any = of();

  ngOnInit(): void {
    this.PostById = this.apollo.watchQuery<{data : Post}>({query : GET_POST}).valueChanges.pipe(map(result => result.data.data)).subscribe()
  }

  log(){
    console.log(this.PostById);
  }

}
