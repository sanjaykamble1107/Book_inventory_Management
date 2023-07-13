import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/Models/category';
import { BookService } from 'src/app/service/book.service';
import { CategoryService } from 'src/app/service/category.service';
import { CommonService } from 'src/app/service/common.service';

@Component({
  selector: 'app-getbycategory',
  templateUrl: './getbycategory.component.html',
  styleUrls: ['./getbycategory.component.css'],
})
export class GetbycategoryComponent implements OnInit {
  searchCriteria: any = {
    category: '',
  };
  booksByCategoryList: any[] = [];
  categoryList:Category[]=[];
  catId!:string;

  constructor(
    private bookService: BookService,
    private commonService: CommonService,
    private categoryService:CategoryService,

  ) { }

  ngOnInit(): void {

  

    if (this.searchCriteria.category == '' && this.commonService.catId != '') {
      this.searchCriteria.category = this.commonService.catId;
      this.commonService.catId = '';
      this.getBooksByCategory();
    }
    else
    this.getCategoryList();

  }

  getCategoryList(){
    this.categoryService.getAllCategories().subscribe(
      (res: any) => {
        this.categoryList = res;
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }

  // Function to fetch books by category
  getBooksByCategory() {
    console.log('getBooksbyCategory called');
    const category = this.searchCriteria.category;
    this.bookService.getBooksByCategory(category).subscribe(
      (response: any) => {
        this.booksByCategoryList = response;
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }
}
