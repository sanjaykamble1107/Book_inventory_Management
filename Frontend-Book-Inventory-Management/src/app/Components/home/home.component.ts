import { Component } from '@angular/core';
import { BookService } from 'src/app/service/book.service';
import { CommonService } from 'src/app/service/common.service';
import { GetbycategoryComponent } from '../book/getbycategory/getbycategory.component';
import { GetCategoryComponent } from '../Category/get-category/get-category.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(private commonService:CommonService){

  }

  getById(num:string){
    console.log("num value :"+num)
    console.log("home getById called")
    this.commonService.setCategoryId(num);
  }


}
