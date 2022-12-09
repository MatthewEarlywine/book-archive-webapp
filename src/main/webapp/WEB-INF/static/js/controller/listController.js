'use strict';

angular.module('myApp').controller('listController', ['$scope', '$log' , 'ListService', function($scope, $log, ListService) {
    var self = this;
    self.book = { id: null, title: '', series: '', author: '', illustrator: '', genre: ''};
    $scope.books = [];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.selectBook = selectBook;
    self.update = update;
    self.check = check;
	
	// form variables
	
	$scope.id=null;
	$scope.title='';
	$scope.series='';
	$scope.author='';
	$scope.illustrator='';
	$scope.genre='';
	$scope.answer='';

    findAllBooks();
	

    function findAllBooks(){
        ListService.fetchAllBooks()
            .then(
            function(d) {
                $scope.books = d;
            },
            function(errResponse){
                $log.error('Error while fetching Books ', errResponse);
            }
        );
    }

	$scope.submit = () => {
		if((($scope.title == '') || ($scope.title == null)) || (( $scope.author == '') || ($scope.author == null))){
			$log.log("Both Title and Author fields are required");
			reset();
			return;
		}
		var newBook = {};
		newBook.id = $scope.id;
		newBook.title = $scope.title;
		newBook.series = $scope.series;
		newBook.author = $scope.author;
		newBook.illustrator = $scope.illustrator;
		newBook.genre = $scope.genre;
		postBook(newBook);
	}

/*	$scope.check = () => {
		for(const book of $scope.books){
			if(($scope.title == book.title) && ($scope.author == book.author)){
				$log.log('This book is already recorded in the archive.');
				System.out.println('This book is already recorded in the archive.')
			} else {
				$log.log('There is no record of this book in the archive.');
				System.out.println('There is no record of this book in the archive.')
			}
		}
	} */
	
	function check(book){
		
		self.book = { id: $scope.id, title: $scope.title, series: $scope.series, author: $scope.author, illustrator: $scope.illustrator, genre: $scope.genre};
		ListService.doesBookExist(self.book)
			.then(
			function(d){
				$scope.answer = d;
			},	
			function(errResponse){
                $log.error('Error while fetching answer ', errResponse);
            }
		);
	}
	
	function postBook(book){
		
		ListService.createBook(book)
			.then(
			function(d){
				if(d != null){
					$scope.books.push(d);
				reset();
				} else {
					reset();
				}

			},
			function(errResponse){
                $log.error('Error while creating Book ', errResponse);
            }	
		);
	}

	function searchForBookById(){
		ListService.searchForBookById(id)
			.then(
			function(d) {
				self.book = d;
			},
			function(errResponse){
                $log.error('Error while fetching Book ', errResponse);
            }
        );
	}
	
	function searchForBookByTitle(){
		ListService.searchForBookByTitle(title)
			.then(
			function(d) {
				self.book = d;
			},
			function(errResponse){
                $log.error('Error while fetching Book ', errResponse);
            }
        );
	}

    function createBook(book){
        ListService.createBook(book)
            .then(
            findAllBooks(),
            function(errResponse){
                $log.error('Error while creating Book ', errResponse);
            }
        );
    }

    function updateBook(book, id){

		$log.log('Updating book', book);
		const i = $scope.books.indexOf(book);
		
        ListService.updateBook(book, id)
            .then(
            function(d){
				$scope.books.splice(i, 1, d);
				findAllBooks();
				$log.log('Updated book', book);
			},
            function(errResponse){
                $log.error('Error while updating Book ', errResponse);
            }
        );
    }

    function deleteBook(book){
        ListService.deleteBook(book)
            .then(
				function(){findAllBooks();}
            ,
            function(errResponse){
                $log.error('Error while deleting Book ', errResponse);
            }
        );
    }

    function submit() {
        if(self.book.id===null){
            $log.log('Saving New Book', self.book);
            createBook(self.book);
        }else{
            updateBook(self.book, self.book.id);
            $log.log('Book updated with id ', self.book.id);
        }
        reset();
    } 

	function selectBook(book) {
		console.log(book);
		$log.log('id to be selected', book.id);
		$scope.id = book.id;
		$scope.title = book.title;
		$scope.series = book.series;
		$scope.author = book.author;
		$scope.illustrator = book.illustrator;
		$scope.genre = book.genre;
		
		
	}

    function edit(book){
    	var id = book.id;
        for(const book of $scope.books){
            if(book.id === id) {
                $scope.id = book.id;
				$scope.title = book.title;
				$scope.series = book.series;
				$scope.author = book.author;
				$scope.illustrator = book.illustrator;
				$scope.genre = book.genre;
				
				$log.log(book);
				$log.log('id to be edited', book.id);
				
				self.book.id = $scope.id;
				$log.log(self.book.id);
            }
        }
    }

    function remove(book){
        $log.log('id to be deleted', book.id);
        //clean form after book is deleted
        if($scope.id === book.id) { 
            reset();
        }
        deleteBook(book);
    }


    function reset(){
		
        $scope.id = '';
		$scope.title = '';
		$scope.series = '';
		$scope.author = '';
		$scope.illustrator = '';
		$scope.genre = '';
        $scope.bookForm.$setPristine();
    }
    
    function update(book) {
		console.log('ID to be updated: ', self.book.id);
		self.book = { id: $scope.id, title: $scope.title, series: $scope.series, author: $scope.author, illustrator: $scope.illustrator, genre: $scope.genre};
		if(((self.book.title == '') || (self.book.title == null)) || ((self.book.author == '') || (self.book.author == null))){
			$log.log("Both Title and Author fields are required");
			reset();
			return;
		}
		updateBook(self.book, self.book.id);
		console.log(self.book);
		console.log('Book updated with id ', self.book.id);
		self.book.id = null;
		reset();
	};

}]);