import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static List<WiseSaying> wiseSayings = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int lastId = 0;
		LocalDateTime localTime = LocalDateTime.now();

		System.out.println("== 명언 앱 실행 ==");

		while (true) {
			System.out.print("명령어 ) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("종료")) {
				System.out.println("== 명언 앱 종료 ==");
				break;
			}

			if (cmd.equals("등록")) {
				int id = lastId + 1;

				System.out.print("명언 : ");
				String content = sc.nextLine().trim();
				System.out.print("작가 : ");
				String author = sc.nextLine().trim();
				LocalDateTime regDate = localTime;

				wiseSayings.add(new WiseSaying(id, regDate, content, author));
				System.out.printf("%d번 명언이 등록되었습니다.\n", id);

				lastId++;
			} else if (cmd.equals("목록")) {
				System.out.println("번호  /  작가  /  명언");
				System.out.println("=".repeat(30));
				for (int i = wiseSayings.size() - 1; i >= 0; i--) {
					WiseSaying wiseSaying = wiseSayings.get(i);
					System.out.printf("%d  /  %s  /  %s\n", wiseSaying.getId(), wiseSaying.getAuthor(),
							wiseSaying.getContent());

				}

			} else if (cmd.startsWith("수정")) {
				String[] cmdBits = cmd.split("\\?");

				String[] paramBits = cmdBits[1].split("=");

//				String key = paramBits[0];
				int value = Integer.parseInt(paramBits[1]);

				WiseSaying wiseSaying = findWiseSaying(value);

				if (wiseSaying == null) {
					System.out.printf("%d번 명언은 존재하지 않습니다.\n", value);
					continue;
				}

				System.out.println("명언(기존) :" + wiseSaying.getContent());
				System.out.println("작가(기존) :" + wiseSaying.getAuthor());
				System.out.print("명언 : ");
				String content = sc.nextLine();
				System.out.print("작가 : ");
				String author = sc.nextLine();

				wiseSaying.setContent(content);
				wiseSaying.setAuthor(author);

				System.out.printf("%d번 명언이 수정되었습니다.\n", value);

			} else if (cmd.startsWith("상세보기")) {
				String[] cmdBits = cmd.split("\\?");

				String[] paramBits = cmdBits[1].split("=");

//				String key = paramBits[0];
				int value = Integer.parseInt(paramBits[1]);

				WiseSaying wiseSaying = findWiseSaying(value);

				if (wiseSaying == null) {
					System.out.printf("%d번 명언은 존재하지 않습니다.\n", value);
					continue;
				}

				System.out.println("번호 : " + wiseSaying.getId());
				System.out.println("날짜 : " + wiseSaying.getRegDate());
				System.out.println("작가 : " + wiseSaying.getAuthor());
				System.out.println("명언 : " + wiseSaying.getContent());

			} else if (cmd.startsWith("삭제")) {
				String[] cmdBits = cmd.split("\\?");

				String[] paramBits = cmdBits[1].split("=");

//				String key = paramBits[0];
				int value = Integer.parseInt(paramBits[1]);

				WiseSaying wiseSaying = findWiseSaying(value);

				if (wiseSaying == null) {
					System.out.printf("%d번 명언은 존재하지 않습니다.\n", value);
					continue;
				}

				wiseSayings.remove(value - 1);

				System.out.printf("%d번 명언이 삭제되었습니다.\n", value);

			}

		}

	}

	public static WiseSaying findWiseSaying(int value) {
		for (WiseSaying wiseSaying : wiseSayings) {
			if (value == wiseSaying.getId()) {
				return wiseSaying;
			}
		}
		return null;
	}

}
