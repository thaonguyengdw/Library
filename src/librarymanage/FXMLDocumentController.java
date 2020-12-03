/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Author;
import pojo.Book;
import pojo.Category;
import pojo.PublishingCompany;
import services.AuthorServices;
import services.CategoryServices;
import services.PublishingCompanyServices;
import services.BookServices;
import services.Utils;


/**
 *
 * @author ngtro_f237v2a
 */
public class FXMLDocumentController implements Initializable {

    @FXML private TableView<Category> tableCategory;
    @FXML private TextField nameCategory;
    @FXML private TextField descCategory;
    
    @FXML private TableView<Author> tableAuthor;   
    @FXML private TextField nicknameAuthor;
    @FXML private TextField nameAuthor;
    @FXML private TextField birthdayAuthor;
    @FXML private TextField genderAuthor;
    @FXML private TextField cityAuthor;
    @FXML private TextField countryAuthor;
    @FXML private TextField backgroundAuthor;
    @FXML private TextField descAuthor;
    
    @FXML private TableView<PublishingCompany> tablePC;
    @FXML private TextField namePC;
    @FXML private TextField phonePC;
    @FXML private TextField addressPC;
    @FXML private TextField descPC;
    
    public ObservableList<Category> options = FXCollections.observableArrayList();
    @FXML private TableView<Book> tableBook;   
    @FXML private TextField nameBook;
    @FXML private ComboBox<Author> cbbAuthor;
    @FXML private ComboBox<Category> cbbCategory;
    @FXML private ComboBox<PublishingCompany> cbbPC;
    @FXML private TextField yearPublishingBook;
    @FXML private TextField entryDateBook;
    @FXML private TextField locationBook;
    @FXML private TextField descBook;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadComboBox();
            createCategoryTable();
            createAuthorTable();
            createPCTable();
            createBookTable();
            this.loadPC();
            this.loadAuthor();
            this.loadCategory();
            this.loadBook();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }   
    public void createCategoryTable(){
        TableColumn col_id = new TableColumn("ID");
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn col_name = new TableColumn("Thể loại");
        col_name.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn col_desc = new TableColumn("Mô tả");
        col_desc.setCellValueFactory(new PropertyValueFactory("description"));
        
        tableCategory.getColumns().addAll(col_id, col_name, col_desc);
        addButtonUpdateCategory();
        addButtonDeleteCategory();

    }
    public void loadCategory() throws SQLException{
        tableCategory.getItems().clear();;
        tableCategory.getItems().clear();
        tableCategory.setItems(FXCollections.observableArrayList(CategoryServices.getCategory(null)));  
   } 
    public void addButtonDeleteCategory(){
        TableColumn col_ActionDelete = new TableColumn();
        col_ActionDelete.setCellFactory(v -> {
            Button btn = new Button("Delete");
            btn.setOnAction(evt -> {
                Category c = (Category) ((TableCell)((Button) evt.getSource()).getParent()).getTableRow().getItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Bạn chắc chắn xóa không?");
                alert.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) 
                        if (CategoryServices.deleteCategory(c.getId()))
                            try {
                                loadCategory();
                            } catch (SQLException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                });
            });
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        tableCategory.getColumns().addAll(col_ActionDelete);
    }
    public void addButtonUpdateCategory(){
        TableColumn col_ActionUpdate = new TableColumn();
        col_ActionUpdate.setCellFactory(v -> {
            Button btn = new Button("Sửa");
            btn.setOnAction(evt -> {
                Category c = tableCategory.getSelectionModel().getSelectedItem();
                    if (c != null)
                    {
                        id_category = c.getId();
                        nameCategory.setText(c.getName());
                        descCategory.setText(c.getDescription());
                        return;
                    }  
            });
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        tableCategory.getColumns().addAll(col_ActionUpdate);
    } 
    private String id_category;
    public void addCategoryHandler(ActionEvent event) throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Bạn muốn thêm không?");
        alert.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) 
            {
                CategoryServices.addCategory(nameCategory.getText(), descCategory.getText());
                alert.setContentText("OK");
                alert.show();
            }
        });
        loadCategory();
    }
    public void editCategoryHandler(ActionEvent event) throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Bạn muốn sửa không?");
        alert.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) 
            {
                CategoryServices.updateCategory(nameCategory.getText(), descCategory.getText(), id_category);
                alert.setContentText("OK");
                alert.show();
            }
        });
        loadCategory();
    }    
    /////////////////////////////////////////////////////////////////////
    public void createAuthorTable(){
        TableColumn col_id = new TableColumn("ID");
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn col_nickname = new TableColumn("Bút danh");
        col_nickname.setCellValueFactory(new PropertyValueFactory("nickname"));
        TableColumn col_name = new TableColumn("Tên thật");
        col_name.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn col_birthday = new TableColumn("Ngày sinh");
        col_birthday.setCellValueFactory(new PropertyValueFactory("birthday"));
        TableColumn col_gender = new TableColumn("Giới tính");
        col_gender.setCellValueFactory(new PropertyValueFactory("gender"));
        TableColumn col_city = new TableColumn("Thành phố");
        col_city.setCellValueFactory(new PropertyValueFactory("city"));
        TableColumn col_country = new TableColumn("Quốc gia");
        col_country.setCellValueFactory(new PropertyValueFactory("country"));
        TableColumn col_background = new TableColumn("Tiểu sử");
        col_background.setCellValueFactory(new PropertyValueFactory("background"));
        TableColumn col_desc = new TableColumn("Mô tả");
        col_desc.setCellValueFactory(new PropertyValueFactory("description"));
        tableAuthor.getColumns().addAll(col_id, col_nickname, col_name,col_birthday,col_gender,col_city,col_country,col_background,col_desc);
        addButtonUpdateAuthor();
        addButtonDeleteAuthor();
    }
    public void loadAuthor() throws SQLException{
        tableAuthor.getItems().clear();
        tableAuthor.setItems(FXCollections.observableArrayList(AuthorServices.getAuthor(null))); 
   } 
    public void addButtonDeleteAuthor(){
        TableColumn col_ActionDelete = new TableColumn();
        col_ActionDelete.setCellFactory(v -> {
            Button btn = new Button("Delete");
            btn.setOnAction(evt -> {
                Author a = (Author) ((TableCell)((Button) evt.getSource()).getParent()).getTableRow().getItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Bạn chắc chắn xóa không?");
                alert.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) 
                        if (AuthorServices.deleteAuthor(a.getId()))
                            try {
                                loadAuthor();
                            } catch (SQLException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                });
            });
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        tableAuthor.getColumns().addAll(col_ActionDelete);
    }
    public void addButtonUpdateAuthor(){
        TableColumn col_ActionUpdate = new TableColumn();
        col_ActionUpdate.setCellFactory(v -> {
            Button btn = new Button("Sửa");
            btn.setOnAction(evt -> {
                Author a = tableAuthor.getSelectionModel().getSelectedItem();
                    if (a != null)
                    {
                        id_author = a.getId();
                        nicknameAuthor.setText(a.getNickname());
                        nameAuthor.setText(a.getName());
                        birthdayAuthor.setText(a.getBirthday());
                        genderAuthor.setText(a.getGender());
                        cityAuthor.setText(a.getCity());
                        countryAuthor.setText(a.getCountry());
                        backgroundAuthor.setText(a.getBackground());
                        descAuthor.setText(a.getDescription());
                    }  
            });
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        tableAuthor.getColumns().addAll(col_ActionUpdate);
    } 
    private String id_author;
    public void addAuthorHandler(ActionEvent event) throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Bạn muốn thêm không?");
        alert.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) 
            {
                AuthorServices.addAuthor(nicknameAuthor.getText(),nameAuthor.getText(),birthdayAuthor.getText(),genderAuthor.getText(),cityAuthor.getText(),countryAuthor.getText(),backgroundAuthor.getText(),descAuthor.getText());
                alert.setContentText("OK");
                alert.show();
            }
        });
        loadAuthor();
    }
    public void editAuthorHandler(ActionEvent event) throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Bạn muốn sửa không?");
        alert.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) 
            {
                AuthorServices.updateAuthor(nicknameAuthor.getText(),nameAuthor.getText(),birthdayAuthor.getText(),genderAuthor.getText(),cityAuthor.getText(),countryAuthor.getText(),backgroundAuthor.getText(),descAuthor.getText(),id_author);
                alert.setContentText("OK");
                alert.show();
            }
        });
        loadAuthor();
    } 
        /////////////////////////////////////////////////////////////////////
    public void createPCTable(){
        TableColumn col_id = new TableColumn("ID");
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn col_name = new TableColumn("NXB");
        col_name.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn col_phone = new TableColumn("SĐT");
        col_phone.setCellValueFactory(new PropertyValueFactory("phone"));
        TableColumn col_address = new TableColumn("Địa chỉ");
        col_address.setCellValueFactory(new PropertyValueFactory("address"));
        TableColumn col_desc = new TableColumn("Mô tả");
        col_desc.setCellValueFactory(new PropertyValueFactory("desc"));
        tablePC.getColumns().addAll(col_id, col_name, col_phone,col_address,col_desc);
        addButtonUpdatePC();
        addButtonDeletePC();
    }
    public void loadPC() throws SQLException{
        tablePC.getItems().clear();
        tablePC.setItems(FXCollections.observableArrayList(PublishingCompanyServices.getPublishingCompany(null))); 
   } 
    public void addButtonDeletePC(){
        TableColumn col_ActionDelete = new TableColumn();
        col_ActionDelete.setCellFactory(v -> {
            Button btn = new Button("Delete");
            btn.setOnAction(evt -> {
                PublishingCompany pc = (PublishingCompany) ((TableCell)((Button) evt.getSource()).getParent()).getTableRow().getItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Bạn chắc chắn xóa không?");
                alert.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) 
                        if (PublishingCompanyServices.deletePublishingCompany(pc.getId()))
                            try {
                                loadPC();
                            } catch (SQLException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                });
            });
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        tablePC.getColumns().addAll(col_ActionDelete);
    }
    public void addButtonUpdatePC(){
        TableColumn col_ActionUpdate = new TableColumn();
        col_ActionUpdate.setCellFactory(v -> {
            Button btn = new Button("Sửa");
            btn.setOnAction(evt -> {
                PublishingCompany pc = tablePC.getSelectionModel().getSelectedItem();
                    if (pc != null)
                    {
                        id_pc = pc.getId();
                        namePC.setText(pc.getName());
                        phonePC.setText(pc.getPhone());
                        addressPC.setText(pc.getAddress());
                        descPC.setText(pc.getDesc());
                    }  
            });
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        tablePC.getColumns().addAll(col_ActionUpdate);
    } 
    private String id_pc;
    public void addPCHandler(ActionEvent event) throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Bạn muốn thêm không?");
        alert.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) 
            {
                PublishingCompanyServices.addPublishingCompany(namePC.getText(),phonePC.getText(),addressPC.getText(),descPC.getText());
                alert.setContentText("OK");
                alert.show();
            }
        });
        loadPC();
    }
    public void editPCHandler(ActionEvent event) throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Bạn muốn sửa không?");
        alert.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) 
            {
                PublishingCompanyServices.updatePublishingCompany(namePC.getText(),phonePC.getText(),addressPC.getText(),descPC.getText(),id_pc);
                alert.setContentText("OK");
                alert.show();
            }
        });
        loadPC();
    } 
    /////////////////////////////////////////////////////////////////////
    public void createBookTable(){
        TableColumn col_id = new TableColumn("ID");
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        
        TableColumn col_name = new TableColumn("Tựa sách");
        col_name.setCellValueFactory(new PropertyValueFactory("name"));
        
        TableColumn col_author_id = new TableColumn("Tác giả");
        col_author_id.setCellValueFactory(new PropertyValueFactory("author_id"));
        
        TableColumn col_category_id = new TableColumn("Thể loại");
        col_category_id.setCellValueFactory(new PropertyValueFactory("category_id"));
        
        TableColumn col_pc_id = new TableColumn("NXB");
        col_pc_id.setCellValueFactory(new PropertyValueFactory("publishing_company_id"));
        
        TableColumn col_publishing_year = new TableColumn("Năm xuất bản");
        col_publishing_year.setCellValueFactory(new PropertyValueFactory("publishing_year"));
        
        TableColumn col_entry_date = new TableColumn("Ngày nhập sách");
        col_entry_date.setCellValueFactory(new PropertyValueFactory("entry_date"));
        
        TableColumn col_location = new TableColumn("Vị trí");
        col_location.setCellValueFactory(new PropertyValueFactory("location"));
        
        TableColumn col_desc = new TableColumn("Mô tả");
        col_desc.setCellValueFactory(new PropertyValueFactory("description"));
        
        tableBook.getColumns().addAll(col_id,col_name,col_author_id,col_category_id,col_pc_id,col_publishing_year,col_entry_date,col_location,col_desc);
        
        addButtonUpdateBook();
        addButtonDeleteBook();
    }
    public void loadBook() throws SQLException{
        tableBook.getItems().clear();
        tableBook.setItems(FXCollections.observableArrayList(BookServices.getBook(null))); 
   } 
    public void addButtonDeleteBook(){
        TableColumn col_ActionDelete = new TableColumn();
        col_ActionDelete.setCellFactory(v -> {
            Button btn = new Button("Delete");
            btn.setOnAction(evt -> {
                Book a = (Book) ((TableCell)((Button) evt.getSource()).getParent()).getTableRow().getItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Bạn chắc chắn xóa không?");
                alert.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) 
                        if (BookServices.deleteBook(a.getId()))
                            try {
                                loadBook();
                            } catch (SQLException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                });
            });
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        tableBook.getColumns().addAll(col_ActionDelete);
    }
    public void addButtonUpdateBook(){
        TableColumn col_ActionUpdate = new TableColumn();
        col_ActionUpdate.setCellFactory(v -> {
            Button btn = new Button("Sửa");
            btn.setOnAction(evt -> {
                Book b = tableBook.getSelectionModel().getSelectedItem();
                    if (b != null)
                    {
                        id_book = b.getId();
                        nameBook.setText(b.getName());
                        yearPublishingBook.setText(b.getPc_year());
                        entryDateBook.setText(b.getEntry_date());
                        locationBook.setText(b.getLocation());
                        descBook.setText(b.getDesc());
                    }  
            });
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        tableBook.getColumns().addAll(col_ActionUpdate);
    } 
    private String id_book;
    public void addBookHandler(ActionEvent event) throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Bạn muốn thêm không?");
        alert.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) 
            {
                BookServices.addBook(nameBook.getText(), getIDccbAuthor(),getIDccbCategory(),getIDccbPC(),yearPublishingBook.getText(),entryDateBook.getText(),locationBook.getText(),descBook.getText());
                alert.setContentText("OK");
                alert.show();
            }
        });
        loadBook();
    }
    public void editBookHandler(ActionEvent event) throws SQLException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Bạn muốn sửa không?");
        alert.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) 
            {
                BookServices.updateBook(nameBook.getText(),getIDccbAuthor(),getIDccbCategory(),getIDccbPC(),yearPublishingBook.getText(),entryDateBook.getText(),locationBook.getText(),descBook.getText(),id_book);
                alert.setContentText("OK");
                alert.show();
            }
        });
        loadBook();
    }
    public void loadComboBox() throws SQLException{
        cbbPC.setItems(FXCollections.observableArrayList(PublishingCompanyServices.getPublishingCompany(null)));
        cbbAuthor.setItems(FXCollections.observableArrayList(AuthorServices.getAuthor(null)));
        cbbCategory.setItems(FXCollections.observableArrayList(CategoryServices.getCategory(null)));
    }
    public String getIDccbAuthor(){
        String id = cbbAuthor.getSelectionModel().getSelectedItem().getId();
        return id;
    }
    public String getIDccbCategory(){
        String id = cbbCategory.getSelectionModel().getSelectedItem().getId();
        return id;
    }
    public String getIDccbPC(){
        String id = cbbPC.getSelectionModel().getSelectedItem().getId();
        return id;
    }
}
