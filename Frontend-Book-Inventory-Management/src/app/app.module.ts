import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { GuestDashboardComponent } from './Components/guest-dashboard/guest-dashboard.component';
import { HomeComponent } from './Components/home/home.component';
import { LoginComponent } from './Components/login/login.component';
import { NavbarComponent } from './Components/navbar/navbar.component';
import { RegisterComponent } from './Components/User/register/register.component';
import { AddnewcategoryComponent } from './Components/Category/addnewcategory/addnewcategory.component';
import { GetallpublisherComponent } from './Components/publisher/getallpublisher/getallpublisher.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { GetAuthorComponent } from './Components/Author/get-author/get-author.component';
import { UpdateAuthorComponent } from './Components/Author/update-author/update-author.component';
import { AddNewBookReviewComponent } from './Components/book-review/add-new-book-review/add-new-book-review.component';
import { BookReviewComponent } from './Components/book-review/book-review.component';
import { GetBookReviewComponent } from './Components/book-review/get-book-review/get-book-review.component';
import { UpdateBookReviewComponent } from './Components/book-review/update-book-review/update-book-review.component';
import { AddbookComponent } from './Components/book/addbook/addbook.component';
import { AllbookComponent } from './Components/book/allbook/allbook.component';
import { GetbycategoryComponent } from './Components/book/getbycategory/getbycategory.component';
import { GetbyisbnComponent } from './Components/book/getbyisbn/getbyisbn.component';
import { GetbypublisheridComponent } from './Components/book/getbypublisherid/getbypublisherid.component';
import { GetbytitleComponent } from './Components/book/getbytitle/getbytitle.component';
import { AddbookconditionComponent } from './Components/BookCondition/addbookcondition/addbookcondition.component';
import { GetbookconditionComponent } from './Components/BookCondition/getbookcondition/getbookcondition.component';
import { UpdatebookdescriptionComponent } from './Components/BookCondition/updatebookdescription/updatebookdescription.component';
import { UpdatebookfulldescriptionComponent } from './Components/BookCondition/updatebookfulldescription/updatebookfulldescription.component';
import { GetCategoryComponent } from './Components/Category/get-category/get-category.component';
import { UpdateCategoryComponent } from './Components/Category/update-category/update-category.component';
import { AddInventoryComponent } from './Components/Inventory/add-inventory/add-inventory.component';
import { SearchInventoryComponent } from './Components/Inventory/search-inventory/search-inventory.component';
import { UpdateInventoryComponent } from './Components/Inventory/update-inventory/update-inventory.component';
import { LandingPageComponent } from './Components/landing-page/landing-page.component';
import { AddNewPermRoleComponent } from './Components/PermRole/add-new-perm-role/add-new-perm-role.component';
import { AddpublisherComponent } from './Components/publisher/addpublisher/addpublisher.component';
import { GetPublisherDetailsComponent } from './Components/publisher/get-publisher-details/get-publisher-details.component';
import { AddPuchaseLogComponent } from './Components/purchaselog/add-puchase-log/add-puchase-log.component';
import { GetPurchaseLogComponent } from './Components/purchaselog/getpurchaselog/getpurchaselog.component';
import { UpdatePurchaseLogComponent } from './Components/purchaselog/update-purchase-log/update-purchase-log.component';
import { AddnewreviewerComponent } from './Components/Reviewer/addnewreviewer/addnewreviewer.component';
import { GetReviewerComponent } from './Components/Reviewer/get-reviewer/get-reviewer.component';
import { UpdateReviewerComponent } from './Components/Reviewer/update-reviewer/update-reviewer.component';
import { AddNewShoppingCartComponent } from './Components/ShoppingCart/add-new-shopping-cart/add-new-shopping-cart.component';
import { AddnewstateComponent } from './Components/State/addnewstate/addnewstate.component';
import { GetallstateComponent } from './Components/State/getallstate/getallstate.component';
import { UpdateStateComponent } from './Components/State/update-state/update-state.component';
import { GetUserComponent } from './Components/User/get-user/get-user.component';
import { UpdateUserComponent } from './Components/User/update-user/update-user.component';
import { StoreOwnerDashboardComponent } from './Components/store-owner-dashboard/store-owner-dashboard.component';
import { RegUserDashboardComponent } from './Components/reg-user-dashboard/reg-user-dashboard.component';
import { AuthGuard } from './Configuration/auth.guard';
import { AuthInterceptor } from './Configuration/authconfig.interceptor';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UpdatebookdetailsComponent } from './Components/book/updatebookdetails/updatebookdetails.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    LoginComponent,
    GuestDashboardComponent,
    RegisterComponent,
    DashboardComponent,
    GetallpublisherComponent,
    AddnewcategoryComponent,
    AddnewreviewerComponent,
    GetallstateComponent,
    AddbookComponent,
    AllbookComponent,
    AddpublisherComponent,
    UpdateStateComponent,
    AddnewstateComponent,
    GetUserComponent,
    GetAuthorComponent,
    LandingPageComponent,
    UpdateCategoryComponent,
    AddpublisherComponent,
    AddPuchaseLogComponent,
    AddNewShoppingCartComponent,
    AddNewPermRoleComponent,
    AddInventoryComponent,
    SearchInventoryComponent,
    UpdateInventoryComponent,
    GetReviewerComponent,
    UpdateReviewerComponent,
    UpdateUserComponent,
    BookReviewComponent,
    AddNewBookReviewComponent,
    GetBookReviewComponent,
    UpdateBookReviewComponent,
    GetCategoryComponent,
    UpdateAuthorComponent,
    AddbookconditionComponent,
    UpdatebookdescriptionComponent,
    UpdatebookfulldescriptionComponent,
    GetPublisherDetailsComponent,
    GetbookconditionComponent,
    GetPurchaseLogComponent,
    UpdatePurchaseLogComponent,
    GetbycategoryComponent,
    GetbyisbnComponent,
    GetbypublisheridComponent,
    GetbytitleComponent,
    StoreOwnerDashboardComponent,
    RegUserDashboardComponent,
    UpdatebookdetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, HttpClientModule, FormsModule, ReactiveFormsModule
  ],
  providers: [
    
    {
    provide:HTTP_INTERCEPTORS,
    useClass:AuthInterceptor,
    multi:true
  },
  AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
