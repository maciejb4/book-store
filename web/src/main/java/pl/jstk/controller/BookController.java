package pl.jstk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jstk.constants.ViewNames;
import pl.jstk.enumerations.BookCategory;
import pl.jstk.repository.CustomRepository;
import pl.jstk.service.BookService;
import pl.jstk.to.BookTo;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/allBooks")
    public String getAllBooks(Model model) {
        model.addAttribute("bookList",bookService.findAllBooks());
        return ViewNames.BOOKS;
    }

    @GetMapping("/book")
    public String getBook(@RequestParam("id") Long id, Model model) {
        model.addAttribute("book",bookService.findById(id));
        return ViewNames.BOOK;
    }

    @GetMapping("/addBook")
    public ModelAndView showFormAddBook() {
        ModelAndView modelAndView = new ModelAndView(ViewNames.ADDBOOK);
        modelAndView.addObject("newBook", new BookTo());
        modelAndView.addObject("categories", BookCategory.values());
        return modelAndView;
    }

    @PostMapping("/addBook")
    public String saveBook(@ModelAttribute("newBook") BookTo bookTo, BindingResult result, final RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            return ViewNames.ADDBOOK;
        }
//        redirectAttributes.addFlashAttribute("message","Book added successfully!");
//        redirectAttributes.addFlashAttribute("alertClass","success");
        BookTo bookTo1 = bookService.saveBook(bookTo);

        return "redirect:/books/book?id=" + bookTo1.getId();
    }

    @GetMapping("/findBook")
    public ModelAndView showFormFindBook(){
        ModelAndView modelAndView = new ModelAndView(ViewNames.FINDBOOK);
        modelAndView.addObject("newBook", new BookTo());
        modelAndView.addObject("categories", BookCategory.values());
        return modelAndView;
    }

    @PostMapping("/findBook")
    public ModelAndView findBooksByCriteria(@ModelAttribute("newBook") BookTo bookTo) {

        List<BookTo> booksByCriteria = bookService.findBooksByCriteria(bookTo);

        ModelAndView modelAndView = new ModelAndView(ViewNames.BOOKS);
        modelAndView.addObject("bookList",booksByCriteria);

        return modelAndView;
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam("id") Long id) {
        bookService.deleteBook(id);
        return ViewNames.BOOKDELETE;
    }
}
