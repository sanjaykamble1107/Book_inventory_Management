import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GetAuthorComponent } from './Components/Author/get-author/get-author.component';
import { UpdateAuthorComponent } from './Components/Author/update-author/update-author.component';
import { AddNewBookReviewComponent } from './Components/book-review/add-new-book-review/add-new-book-review.component';
import { GetBookReviewComponent } from './Components/book-review/get-book-review/get-book-review.component';
import { UpdateBookReviewComponent } from './Components/book-review/update-book-review/update-book-review.component';
import { AddbookComponent } from './Components/book/addbook/addbook.component';
import { AllbookComponent } from './Components/book/allbook/allbook.component';
import { GetbycategoryComponent } from './Components/book/getbycategory/getbycategory.component';
import { GetbyisbnComponent } from './Components/book/getbyisbn/getbyisbn.component';
import { GetbypublisheridComponent } from './Components/book/getbypublisherid/getbypublisherid.component';
import { GetbytitleComponent } from './Components/book/getbytitle/getbytitle.component';
import { UpdatebookdetailsComponent } from './Components/book/updatebookdetails/updatebookdetails.component';
import { AddbookconditionComponent } from './Components/BookCondition/addbookcondition/addbookcondition.component';
import { GetbookconditionComponent } from './Components/BookCondition/getbookcondition/getbookcondition.component';
import { UpdatebookdescriptionComponent } from './Components/BookCondition/updatebookdescription/updatebookdescription.component';
import { UpdatebookfulldescriptionComponent } from './Components/BookCondition/updatebookfulldescription/updatebookfulldescription.component';
import { AddnewcategoryComponent } from './Components/Category/addnewcategory/addnewcategory.component';
import { GetCategoryComponent } from './Components/Category/get-category/get-category.component';
import { UpdateCategoryComponent } from './Components/Category/update-category/update-category.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { GuestDashboardComponent } from './Components/guest-dashboard/guest-dashboard.component';
import { HomeComponent } from './Components/home/home.component';
import { AddInventoryComponent } from './Components/Inventory/add-inventory/add-inventory.component';
import { SearchInventoryComponent } from './Components/Inventory/search-inventory/search-inventory.component';
import { UpdateInventoryComponent } from './Components/Inventory/update-inventory/update-inventory.component';
import { LoginComponent } from './Components/login/login.component';
import { AddNewPermRoleComponent } from './Components/PermRole/add-new-perm-role/add-new-perm-role.component';
import { AddpublisherComponent } from './Components/publisher/addpublisher/addpublisher.component';
import { GetPublisherDetailsComponent } from './Components/publisher/get-publisher-details/get-publisher-details.component';
import { GetallpublisherComponent } from './Components/publisher/getallpublisher/getallpublisher.component';
import { AddPuchaseLogComponent } from './Components/purchaselog/add-puchase-log/add-puchase-log.component';
import { GetPurchaseLogComponent } from './Components/purchaselog/getpurchaselog/getpurchaselog.component';
import { UpdatePurchaseLogComponent } from './Components/purchaselog/update-purchase-log/update-purchase-log.component';
import { RegUserDashboardComponent } from './Components/reg-user-dashboard/reg-user-dashboard.component';
import { AddnewreviewerComponent } from './Components/Reviewer/addnewreviewer/addnewreviewer.component';
import { GetReviewerComponent } from './Components/Reviewer/get-reviewer/get-reviewer.component';
import { UpdateReviewerComponent } from './Components/Reviewer/update-reviewer/update-reviewer.component';
import { AddnewstateComponent } from './Components/State/addnewstate/addnewstate.component';
import { GetallstateComponent } from './Components/State/getallstate/getallstate.component';
import { UpdateStateComponent } from './Components/State/update-state/update-state.component';
import { StoreOwnerDashboardComponent } from './Components/store-owner-dashboard/store-owner-dashboard.component';
import { GetUserComponent } from './Components/User/get-user/get-user.component';
import { RegisterComponent } from './Components/User/register/register.component';
import { UpdateUserComponent } from './Components/User/update-user/update-user.component';
import { AuthGuard } from './Configuration/auth.guard';

const routes: Routes = [
  // Book
  {
    path: 'addbook',
    component: AddbookComponent,canActivate:[AuthGuard],data:{roleNumber:["Admin"]}
  },
  {
    path: 'allbook',
    component: AllbookComponent,canActivate:[AuthGuard],data:{roleNumber:["Guest","RegisteredUser","StoreOwner","Admin"]}
  },
  {
    path: 'getbyisbn',
    component: GetbyisbnComponent,canActivate:[AuthGuard],data:{roleNumber:["Guest","RegisteredUser","StoreOwner","Admin"]}
  },
  {
    path: 'getbycategory',
    component: GetbycategoryComponent
  },
  {
    path: 'getbypublisherid',
    component: GetbypublisheridComponent,canActivate:[AuthGuard],data:{roleNumber:["Guest","RegisteredUser","StoreOwner","Admin"]}
  },
  {
    path: 'getbytitle',
    component: GetbytitleComponent,canActivate:[AuthGuard],data:{roleNumber:["Guest","RegisteredUser","StoreOwner","Admin"]}
  },
  {
    path: 'updatebookdescription',
    component: UpdatebookdescriptionComponent,canActivate:[AuthGuard],data:{roleNumber:["Admin"]}
  },
  {
    path: 'updatebookfulldescription',
    component: UpdatebookfulldescriptionComponent,canActivate:[AuthGuard],data:{roleNumber:["Admin"]}
  },
  {
   path:'updatebookdetails',
   component:UpdatebookdetailsComponent,canActivate:[AuthGuard],data:{roleNumber:["Admin"]}
  },
  //Book Condition

  {
    path: 'addbookcondition',
    component: AddbookconditionComponent,canActivate:[AuthGuard],data:{roleNumber:["StoreOwner","Admin"]}
  },
  {
    path: 'getbookcondition',
    component: GetbookconditionComponent,canActivate:[AuthGuard],data:{roleNumber:["Guest","RegisteredUser","StoreOwner","Admin"]}
  },
  //Author
  {
    path: 'get-author',
    component: GetAuthorComponent,canActivate:[AuthGuard],data:{roleNumber:["Guest","RegisteredUser","StoreOwner","Admin"]}
  },
  {
    path: 'update-author',
    component: UpdateAuthorComponent,canActivate:[AuthGuard],data:{roleNumber:["Admin"]}
  },
  //User
  {
    path: 'get-user',
    component: GetUserComponent,canActivate:[AuthGuard],data:{roleNumber:["StoreOwner","Admin"]}
  },
  {
    path: 'update-user',
    component: UpdateUserComponent,canActivate:[AuthGuard],data:{roleNumber:["RegisteredUser","Admin"]}
  },
  //State
  {
    path: 'addnewstate',
    component: AddnewstateComponent,canActivate:[AuthGuard],data:{roleNumber:["Admin"]}
  },
  {
    path: 'getallstate',
    component: GetallstateComponent,canActivate:[AuthGuard],data:{roleNumber:["Guest","RegisteredUser","StoreOwner","Admin"]}
  },
  {
    path: 'update-state',
    component: UpdateStateComponent,canActivate:[AuthGuard],data:{roleNumber:["Admin"]}
  },
  //Purchase Log
  {
    path: 'update-purchase-log',
    component: UpdatePurchaseLogComponent,canActivate:[AuthGuard],data:{roleNumber:["StoreOwner","Admin"]}
  },
  {
    path: 'getpurchaselog',
    component: GetPurchaseLogComponent,canActivate:[AuthGuard],data:{roleNumber:["StoreOwner","Admin"]}
  },
  {
    path: 'add-puchase-log',
    component: AddPuchaseLogComponent,canActivate:[AuthGuard],data:{roleNumber:["StoreOwner","Admin"]}
  },

  //Publisher
  {
    path: 'addpublisher',
    component: AddpublisherComponent, canActivate: [AuthGuard], data: { roleNumber: ["Admin"] }
  },
  {
    path: 'getallpublisher',
    component: GetallpublisherComponent,canActivate:[AuthGuard],data:{roleNumber:["Guest","RegisteredUser","StoreOwner","Admin"]}
  },
  {
    path: 'get-publisher-details',
    component: GetPublisherDetailsComponent, canActivate: [AuthGuard], data: { roleNumber: ["Admin"] }
  },
  //Category
  {
    path: 'addnewcategory',
    component: AddnewcategoryComponent, canActivate: [AuthGuard], data: { roleNumber: ["Admin"] }
  },
  {
    path: 'updateCategory',
    component: UpdateCategoryComponent, canActivate: [AuthGuard], data: { roleNumber: ["Admin"] }
  },
  {
    path: 'get-category',
    component: GetCategoryComponent,canActivate:[AuthGuard],data:{roleNumber:["Guest","RegisteredUser","StoreOwner","Admin"]}
  },
  //Book Review
  {
    path: 'update-book-review',
    component: UpdateBookReviewComponent,canActivate:[AuthGuard],data:{roleNumber:["RegisteredUser","Admin"]}
  },
  {
    path: 'get-book-review',
    component: GetBookReviewComponent,canActivate:[AuthGuard],data:{roleNumber:["Guest","RegisteredUser","StoreOwner","Admin"]}
  },
  {
    path: 'add-new-book-review',
    component: AddNewBookReviewComponent,canActivate:[AuthGuard],data:{roleNumber:["RegisteredUser","StoreOwner","Admin"]}

  },
  //Reviewer
  {
    path: 'addnewreviewer',
    component: AddnewreviewerComponent,canActivate:[AuthGuard],data:{roleNumber:["Admin"]}
  },
  {
    path: 'update-reviewer',
    component: UpdateReviewerComponent,canActivate:[AuthGuard],data:{roleNumber:["Admin"]}
  },
  {
    path: 'get-reviewer',
    component: GetReviewerComponent,canActivate:[AuthGuard],data:{roleNumber:["StoreOwner","Admin"]}
  },
  //Inventory
  {
    path: 'update-inventory',
    component: UpdateInventoryComponent,canActivate:[AuthGuard],data:{roleNumber:["StoreOwner","Admin"]}
  },
  {
    path: 'search-inventory',
    component: SearchInventoryComponent,canActivate:[AuthGuard],data:{roleNumber:["StoreOwner","Admin"]}
  },
  {
    path: 'add-inventory',
    component: AddInventoryComponent,canActivate:[AuthGuard],data:{roleNumber:["StoreOwner","Admin"]}
  },
  //PermRole
  {
    path: 'add-new-perm-role',
    component: AddNewPermRoleComponent,canActivate:[AuthGuard],data:{roleNumber:["Admin"]}
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
          //Dashboards
  {
    path: 'reg-user-dashboard',
    component: RegUserDashboardComponent, canActivate: [AuthGuard], data: { roleNumber: ["RegisteredUser"] }
  },
  {
    path: 'guest-dashboard',
    component: GuestDashboardComponent, canActivate: [AuthGuard], data: { roleNumber: ["Guest"] }
  },
  {
    path: 'store-owner-dashboard',
    component: StoreOwnerDashboardComponent, canActivate: [AuthGuard], data: { roleNumber: ["StoreOwner"] }
  },
  {
    path: 'dashboard',
    component: DashboardComponent,canActivate:[AuthGuard],data:{roleNumber:["Admin"]}
  },
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent
  },
  
  {
    path: '**',
    redirectTo: 'home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
