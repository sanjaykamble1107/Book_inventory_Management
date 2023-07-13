import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-addnewcategory',
  templateUrl: './addnewcategory.component.html',
  styleUrls: ['./addnewcategory.component.css']
})
export class AddnewcategoryComponent {

  category: any = {};

  constructor(private categoryService: CategoryService) { }

  addCategory() {
    this.categoryService.addCategory(this.category)
      .subscribe(
        response => {
          console.log('Category added successfully');
          alert("Category added successfully")
        },
        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);

        }
      );
  }

}
