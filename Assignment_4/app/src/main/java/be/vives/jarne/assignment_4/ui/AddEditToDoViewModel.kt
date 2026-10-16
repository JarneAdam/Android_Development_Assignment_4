package be.vives.jarne.assignment_4.ui

import androidx.lifecycle.ViewModel
import be.vives.jarne.assignment_4.models.MockupToDo
import be.vives.jarne.assignment_4.models.ToDo
import be.vives.jarne.assignment_4.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AddEditToDoUiState(
    val title: String = "",
    val description: String = "",
    val selectedUser: User? = null,
    val users: List<User> = emptyList(),
    val analysisDone: Boolean = false,
    val developmentDone: Boolean = false,
    val reviewAndTestingDone: Boolean = false,
    val acceptanceDone: Boolean = false,
    val initialToDo: ToDo? = null,
)

class AddEditToDoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        AddEditToDoUiState(
            users = MockupToDo.getUsers(),
        )
    )
    val uiState: StateFlow<AddEditToDoUiState> = _uiState.asStateFlow()

    fun setInitialToDo(toDo: ToDo?, users: List<User> = MockupToDo.getUsers()) {
        if (toDo != null) {
            _uiState.update {
                it.copy(
                    title = toDo.title,
                    description = toDo.description,
                    selectedUser = toDo.assignedToUser,
                    users = users,
                    analysisDone = toDo.analysisDone,
                    developmentDone = toDo.developmentDone,
                    reviewAndTestingDone = toDo.reviewAndTestingDone,
                    acceptanceDone = toDo.acceptanceDone,
                    initialToDo = toDo,
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    users = users,
                    initialToDo = null,
                )
            }
        }
    }

    fun onTitleChange(newTitle: String) {
        _uiState.update { it.copy(title = newTitle) }
    }

    fun onDescriptionChange(newDescription: String) {
        _uiState.update { it.copy(description = newDescription) }
    }

    fun onUserSelected(user: User?) {
        _uiState.update { it.copy(selectedUser = user) }
    }

    fun onAnalysisDoneChange(done: Boolean) {
        _uiState.update { it.copy(analysisDone = done) }
    }

    fun onDevelopmentDoneChange(done: Boolean) {
        _uiState.update { it.copy(developmentDone = done) }
    }

    fun onReviewAndTestingDoneChange(done: Boolean) {
        _uiState.update { it.copy(reviewAndTestingDone = done) }
    }

    fun onAcceptanceDoneChange(done: Boolean) {
        _uiState.update { it.copy(acceptanceDone = done) }
    }

    fun getUpdatedToDo(): ToDo {
        val state = _uiState.value
        val baseToDo = state.initialToDo ?: MockupToDo.getToDos().first()
        return baseToDo.copy(
            title = state.title,
            description = state.description,
            assignedToUser = state.selectedUser,
            analysisDone = state.analysisDone,
            developmentDone = state.developmentDone,
            reviewAndTestingDone = state.reviewAndTestingDone,
            acceptanceDone = state.acceptanceDone,
        )
    }
}
