package teistermask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import teistermask.bindingModel.TaskBindingModel;
import teistermask.entity.Task;
import teistermask.repository.TaskRepository;

import java.util.List;

@Controller
public class TaskController {
	private final TaskRepository taskRepository;

	@Autowired
	public TaskController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@GetMapping("/")
	public String index(Model model) {
		List<Task> openTasks = taskRepository.findAllByStatus("Open");
		List<Task> inProgressTasks = taskRepository.findAllByStatus("In Progress");
		List<Task> finishedTasks = taskRepository.findAllByStatus("Finished");

		model.addAttribute("openTasks", openTasks);
		model.addAttribute("inProgressTasks", inProgressTasks);
		model.addAttribute("finishedTasks", finishedTasks);

		model.addAttribute("view", "task/index");

		return "base-layout";	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("view", "task/create");

		return "base-layout";
	}

	@PostMapping("/create")
	public String createProcess(Model model, Task taskModel) {
		if (model == null) {
			return "redirect:/";
		}

		if (taskModel.getTitle().equals("") || taskModel.getStatus().equals("")) {
			return "redirect:/";
		}

		Task task = new Task(taskModel.getTitle(), taskModel.getStatus());

		taskRepository.saveAndFlush(task);

		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable int id) {
		Task task = taskRepository.findOne(id);

		if (task == null ) {
			return "redirect:/";
		}

		model.addAttribute("task", task);
		model.addAttribute("view", "task/edit");

		return "base-layout";
	}

	@PostMapping("/edit/{id}")
	public String editProcess(Model model, @PathVariable int id, Task taskModel) {
		if (taskModel.getTitle().equals("") || taskModel.getStatus().equals("")) {
			taskModel.setId(id);

			model.addAttribute("task", taskModel);
			model.addAttribute("view", "task/edit");

			return "base-layout";
		}

		Task task = taskRepository.findOne(id);

		if (task == null ) {
			return "redirect:/";
		}
		task.setTitle(taskModel.getTitle());
		task.setStatus(taskModel.getStatus());
		taskRepository.saveAndFlush(task);
		return "redirect:/";
}
}
