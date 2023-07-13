import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-update-category',
  templateUrl: './update-category.component.html',
  styleUrls: ['./update-category.component.css']
})
export class UpdateCategoryComponent {
  catId!: number;
  category: any = {};

  constructor(private categoryService: CategoryService) { }

  updateCategory() {
    this.categoryService.updateCategory(this.catId, this.category).subscribe(
      response => {
        console.log('Category updated successfully');
        alert("updated successfully");
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }
}
